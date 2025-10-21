package com.miniorm.models;

import com.miniorm.annotations.*;
import com.miniorm.enums.GenerationType;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity(tableName = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")   private String name;
    @Column(name = "price")  private BigDecimal price;

    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;

    @Override public String toString() {
        return "Product { id='" + id + "', name='" + name + "', price=" + price +
               ", createdAt='" + createdAt + "', updatedAt='" + updatedAt + "' }";
    }
}