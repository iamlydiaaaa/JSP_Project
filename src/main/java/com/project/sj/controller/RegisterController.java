package com.project.sj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.sj.SingletonService;
import com.project.sj.domain.User;
import com.project.sj.service.UserService;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/register")
@Slf4j
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("register ... get");
		resp.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("register ... post");
		SingletonService instance = SingletonService.getInstance();
		UserService userService = instance.userService();
	
		String phone = req.getParameter("phone1") 
				+ req.getParameter("phone2") 
				+ req.getParameter("phone3");
		Integer age=0;
		Integer gender=0;
		User user = User.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
//				.regDate(req.getParameter("regDate")) 나중에 테스트
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.phone(phone)
				.age(age)
				.gender(gender)
				.build();
		//user의 정보를 받아서 회원가입으로 보낸다
		userService.join(user);
		req.setAttribute("user", user.getId());
		resp.sendRedirect("/");
	}
}
