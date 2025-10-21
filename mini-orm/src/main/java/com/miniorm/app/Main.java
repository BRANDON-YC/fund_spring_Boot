package com.miniorm.app;

import com.miniorm.core.EntityManager;
import com.miniorm.dto.RegisterProductDto;
import com.miniorm.dto.RegisterUserDto;
import com.miniorm.models.Product;
import com.miniorm.models.User;
import com.miniorm.repository.GenericRepository;
import com.miniorm.service.ProductService;
import com.miniorm.service.UserService;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        var em = new EntityManager();

        GenericRepository<UUID, User> userRepo = em.getRepository(User.class);
        GenericRepository<UUID, Product> productRepo = em.getRepository(Product.class);

        var userService = new UserService(userRepo);
        var productService = new ProductService(productRepo);

        // Users
        userService.createUser(new RegisterUserDto("John Doe","john.doe@example.com","password123"));
        userService.createUser(new RegisterUserDto("Jane Smith","jane.smith@gmail.com","securepass"));

        userService.listAll().forEach(u -> {
            System.out.println("ID: " + u.getId() + ",");
            System.out.println("User: " + u);
        });

        System.out.println("--- Products ---");
        // Products
        var p1 = productService.create(new RegisterProductDto("Gorra AgroBoys", new BigDecimal("79.90")));
        var p2 = productService.create(new RegisterProductDto("Beanie Classic", new BigDecimal("59.50")));

        productService.list().forEach(p -> {
            System.out.println("ID: " + p.getId() + ",");
            System.out.println("Product: " + p);
        });

        // Update + Delete demo
        productService.update(p1.getId(), new Product(null, "Gorra AgroBoys PRO", new BigDecimal("89.90"), null, null));
        productService.delete(p2.getId());
        System.out.println("--- After updates ---");
        productService.list().forEach(System.out::println);
    }
}