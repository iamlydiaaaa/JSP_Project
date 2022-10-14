package com.example.repository;


import com.example.domain.User;

import java.util.Optional;

public interface UserRepository {
    void insert(User user);

    Optional<User> getById(String id);
}
