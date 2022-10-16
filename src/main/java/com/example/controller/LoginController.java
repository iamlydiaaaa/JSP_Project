package com.example.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends UserController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login get ...");
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        req.getRequestDispatcher("login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login post ...");

        HttpSession session = req.getSession();
        resp.setCharacterEncoding("UTF-8");
        String msg = "";

        //id pwd chk(1=아이디기억 , 2=로그인유지)
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String chk2 = req.getParameter("chk2");

        //로그인체크
        try {
            if (userService.login(id, pwd)) {
                System.out.println("로그인 성공");
                //로그인 성공후 로그인 유지 기능이 체크되어 있으면
                if("2".equals(chk2)){
                    msg = URLEncoder.encode("remember_login", StandardCharsets.UTF_8);
                }
                //관리자모드
                if ("admin".equals(id)) {
                    session.setAttribute("user", "admin");
                }
                //유저모드
                else {
                    session.setAttribute("user", id);
                }
                //로그인 성공시 홈으로 리다이렉트
                resp.sendRedirect("/project?msg="+msg);
            }//if
            else{
                throw new RuntimeException("로그인 실패");
            }
        } catch (Exception e) {
            //로그인 실패시 login ... get
            e.printStackTrace();
            msg = URLEncoder.encode("아이디와 비밀번호를 확인해주세요", StandardCharsets.UTF_8);
            resp.sendRedirect("/project/login?msg="+msg);
        }
    }
}
