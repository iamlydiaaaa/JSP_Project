package com.example.service;


import com.example.domain.User;
import com.example.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public void join(User user) {
        userRepository.insert(user);
    }

    @Override
    public boolean login(String id, String pwd) {
        //입력된 id로 유저 객체를 받아옴
        //유저 정보를 가져오는데 실패하면 예외를 던짐
        User user = getUSer(id).orElseThrow();
        if (user.getId() == "" || user.getId() == null ||
                user.getPwd() =="" || user.getPwd() == null) {
            return false;
        }
        //id==pwd 일치하는지
		return pwd.equals(user.getPwd());
	}

    @Override
    public Optional<User> getUSer(String id) {
        return userRepository.getById(id);
    }
}