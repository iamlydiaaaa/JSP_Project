package com.example.service;



import com.example.config.SingletonProvider;
import com.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserServiceTest {
    UserService userService = SingletonProvider.getInstance().userService();

    @Test
    @DisplayName("유저 조회")
    public void selectTest(){
        Optional<User> result = userService.getUSer("user1");
        User user = result.get();
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginTest1(){
        boolean login = userService.login("user1", "user1");
        Assertions.assertTrue(login);
    }

    @Test
    @DisplayName("로그인 실패")
    public void loginTest2(){
        Assertions.assertThrows(RuntimeException.class,
                ()->userService.login("user2", "user2"));
    }
}
