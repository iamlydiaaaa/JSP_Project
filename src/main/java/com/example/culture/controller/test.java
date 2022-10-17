package com.example.culture.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/test")
public class test extends CultureController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = cultureService.test();
        HttpSession session = req.getSession();
//        session.setAttribute("test",result);
        req.setAttribute("test",result);
        req.getRequestDispatcher("test.jsp").forward(req,resp);
    }
}
