package com.miniorm.core;
import com.miniorm.repository.GenericRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class EntityManager {
    private final Map<Class<?>, GenericRepository<?, ?>> cache = new ConcurrentHashMap<>();
    public <ID, T> GenericRepository<ID, T> getRepository(Class<T> clazz) {
        return (GenericRepository<ID, T>) cache.computeIfAbsent(clazz, c -> new InMemoryRepository<>(clazz));
    }
}