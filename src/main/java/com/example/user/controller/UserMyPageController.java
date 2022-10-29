package com.example.user.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="userMyPageController",value="/myPage")
@Slf4j
public class UserMyPageController extends UserController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("userMyPageController.doGet");
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("logined_cookie")){
                session.setAttribute("user", URLEncoder.encode(cookie.getValue(), StandardCharsets.UTF_8));
            }
        }
        if(session.getAttribute("user")==null){
            throw new IllegalStateException("비로그인 예외");
        }
        String id = (String) session.getAttribute("user");
//        req.setAttribute("user",userService.getUser(id));
        req.getRequestDispatcher("WEB-INF/view/user/myPage.jsp").forward(req,resp);
    }
}
