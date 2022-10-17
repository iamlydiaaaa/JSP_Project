package com.example.user.service;


import com.example.domain.User;

import java.util.Optional;

public interface UserService {
    //회원가입
    void join(User user);
    //로그인
    boolean login(String id, String pwd);
    //유저정보 조회
    Optional<User> getUSer(String id);
}
