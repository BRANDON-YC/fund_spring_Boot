package com.miniorm.service;

import com.miniorm.dto.RegisterProductDto;
import com.miniorm.models.Product;
import com.miniorm.repository.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private final GenericRepository<UUID, Product> repo;

    public ProductService(GenericRepository<UUID, Product> repo) {
        this.repo = repo;
    }

    public Product create(RegisterProductDto dto) {
        var now = LocalDateTime.now();
        var p = new Product(null, dto.name(), dto.price(), now, now);
        return repo.save(p);
    }

    public Product get(UUID id) { return repo.findById(id).orElse(null); }
    public List<Product> list() { return repo.findAll(); }

    public Product update(UUID id, Product patch) {
        var current = get(id);
        if (current == null) return null;
        if (patch.getName() != null) current.setName(patch.getName());
        if (patch.getPrice() != null) current.setPrice(patch.getPrice());
        current.setUpdatedAt(java.time.LocalDateTime.now());
        return repo.update(id, current);
    }

    public boolean delete(UUID id) { return repo.deleteById(id); }
}