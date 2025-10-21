package com.miniorm.service;

import com.miniorm.dto.RegisterUserDto;
import com.miniorm.models.User;
import com.miniorm.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class UserService {
    private final GenericRepository<UUID, User> repo;

    public User createUser(RegisterUserDto dto) {
        var now = LocalDateTime.now();
        var user = new User(null, dto.name(), dto.email(), dto.password(), now, now);
        return repo.save(user);
    }

    public User getUserById(UUID id) { return repo.findById(id).orElse(null); }
    public List<User> listAll() { return repo.findAll(); }

    public User updateUser(UUID id, User patch) {
        var current = getUserById(id);
        if (current == null) return null;
        if (patch.getName() != null) current.setName(patch.getName());
        if (patch.getEmail() != null) current.setEmail(patch.getEmail());
        if (patch.getPassword() != null) current.setPassword(patch.getPassword());
        current.setUpdatedAt(LocalDateTime.now());
        return repo.update(id, current);
    }

    public boolean deleteUser(UUID id) { return repo.deleteById(id); }
}