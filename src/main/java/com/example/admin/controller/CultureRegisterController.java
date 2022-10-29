package com.example.admin.controller;

import com.example.culture.service.CultureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="cultureRegisterController",value="/culture/register")
@RequiredArgsConstructor
@Slf4j
public class CultureRegisterController extends HttpServlet {

    private final CultureService cultureService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("register ... get");
        req.getRequestDispatcher("WEB-INF/view/culture/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("register ... post");
        cultureService.register();
        //
        log.info("db 최신화 성공");
        resp.sendRedirect("/project");
    }
}
