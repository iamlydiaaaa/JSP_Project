package com.example.user.dao;


import com.example.domain.UserVO;

import java.util.Optional;

public interface UserDAO {
    void insert(UserVO userVO);

    Optional<UserVO> getById(String id);
}
