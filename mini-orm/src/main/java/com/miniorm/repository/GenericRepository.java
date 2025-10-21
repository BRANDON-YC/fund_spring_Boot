package com.miniorm.repository;
import java.util.List;
import java.util.Optional;
public interface GenericRepository<ID, T> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    T update(ID id, T entity);
    boolean deleteById(ID id);
}