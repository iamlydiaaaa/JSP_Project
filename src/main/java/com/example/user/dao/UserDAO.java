package com.example.user.dao;


import com.example.user.vo.UserVO;

import java.util.Optional;

public interface UserDAO {
    void insert(UserVO userVO);

    Optional<UserVO> getById(String id);
}
