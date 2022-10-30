package com.example.user.controller;

import com.example.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="userCheckController",value="/check")
@Slf4j
public class UserCheckController extends UserController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("UserCheckController.doGet");
            String id = req.getParameter("id");
            UserVO user = userService.getUser(id);
            System.out.println("user = " + user);
            if(user==null){
                resp.setStatus(200);
            }
            else{
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("중복 아이디 조회 에러");
        }
    }
}
