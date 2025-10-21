package com.miniorm.models;

import com.miniorm.annotations.*;
import com.miniorm.enums.GenerationType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity(tableName = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")      private String name;
    @Column(name = "email")     private String email;
    @Column(name = "password")  private String password;
    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;
}