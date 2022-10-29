package com.example.user.service;


import com.example.user.vo.UserVO;

import java.util.Optional;

public interface UserService {
    //회원가입
    void join(UserVO userVO);
    //로그인
    boolean login(String id, String pwd);
    //유저정보 조회
    Optional<UserVO> getUser(String id);
}
