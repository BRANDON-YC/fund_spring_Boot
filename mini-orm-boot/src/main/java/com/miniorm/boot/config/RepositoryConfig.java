package com.miniorm.boot.config;

import com.miniorm.core.InMemoryRepository;
import com.miniorm.models.Product;
import com.miniorm.models.User;
import com.miniorm.repository.GenericRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RepositoryConfig {
    @Bean public GenericRepository<UUID, User> userRepository() {
        return new InMemoryRepository<>(User.class);
    }
    @Bean public GenericRepository<UUID, Product> productRepository() {
        return new InMemoryRepository<>(Product.class);
    }
}