package com.example.controller;

import com.example.service.RegisterService;
import com.example.service.SingletonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="registerController",value="/register")
public class RegisterController extends HttpServlet {

    SingletonService instance = SingletonService.getInstance();
    RegisterService registerService = instance.registerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... get");
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... post");
        //다음달 내용 업데이트 위해 현재 culture의 모든 내용을 전부 삭제 시키는 코드 추가 해야함

        //
        registerService.register();
        System.out.println("db 최신화 성공");
        resp.sendRedirect("/project");
    }
}
