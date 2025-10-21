package com.miniorm.core;

import com.miniorm.annotations.GeneratedValue;
import com.miniorm.annotations.Id;
import com.miniorm.enums.GenerationType;
import com.miniorm.exceptions.MiniOrmException;
import com.miniorm.repository.GenericRepository;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("unchecked")
public class InMemoryRepository<ID, T> implements GenericRepository<ID, T> {

    private final Class<T> entityClass;
    private final Map<ID, T> storage = new ConcurrentHashMap<>();
    private final Field idField;
    private final GenerationType idStrategy;
    private final AtomicLong seq = new AtomicLong(1);

    public InMemoryRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.idField = resolveIdField(entityClass);
        this.idField.setAccessible(true);
        this.idStrategy = resolveStrategy(idField);
    }

    private static Field resolveIdField(Class<?> type) {
        for (Field f : type.getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) return f;
        }
        throw new MiniOrmException("No @Id field found in " + type.getName());
    }

    private static GenerationType resolveStrategy(Field idField) {
        GeneratedValue gv = idField.getAnnotation(GeneratedValue.class);
        return gv == null ? GenerationType.AUTO_INCREMENT : gv.strategy();
    }

    private ID getId(T entity) {
        try { return (ID) idField.get(entity); }
        catch (IllegalAccessException e) { throw new MiniOrmException("Cannot read @Id", e); }
    }

    private void setId(T entity, ID id) {
        try { idField.set(entity, id); }
        catch (IllegalAccessException e) { throw new MiniOrmException("Cannot write @Id", e); }
    }

    private ID generateId() {
        Class<?> idType = idField.getType();
        return (ID) switch (idStrategy) {
            case UUID -> java.util.UUID.randomUUID();
            case AUTO_INCREMENT, IDENTITY, SEQUENCE, TABLE -> {
                long n = seq.getAndIncrement();
                if (idType.equals(Long.class) || idType.equals(long.class)) yield n;
                if (idType.equals(Integer.class) || idType.equals(int.class)) yield (int) n;
                if (idType.equals(String.class)) yield String.valueOf(n);
                if (idType.equals(BigInteger.class)) yield java.math.BigInteger.valueOf(n);
                throw new MiniOrmException("AUTO id no soportado para tipo: " + idType.getName());
            }
        };
    }

    private boolean isNullOrZero(Object id) {
        if (id == null) return true;
        if (id instanceof Number num) return num.longValue() == 0L;
        if (id instanceof java.util.UUID u) return u.equals(new java.util.UUID(0,0));
        return false;
    }

    @Override
    public T save(T entity) {
        ID id = getId(entity);
        if (isNullOrZero(id)) {
            id = generateId();
            setId(entity, id);
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T update(ID id, T entity) {
        if (!storage.containsKey(id)) return null;
        setId(entity, id);
        storage.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(ID id) {
        return storage.remove(id) != null;
    }
}