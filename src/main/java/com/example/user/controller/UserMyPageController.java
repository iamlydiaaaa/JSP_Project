package com.example.user.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="userMyPageController",value="/myPage")
@Slf4j
public class UserMyPageController extends UserController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("userMyPageController.doGet");
        req.getRequestDispatcher("WEB-INF/view/user/myPage.jsp").forward(req,resp);
    }
}
