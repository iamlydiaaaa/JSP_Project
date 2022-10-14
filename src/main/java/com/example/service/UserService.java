package com.example.service;


import com.example.domain.User;

import java.util.Optional;

public interface UserService {
    void join(User user);

    boolean login(String id, String pwd);

    Optional<User> getUSer(String id);
}
