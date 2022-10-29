package com.example.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebFilter(filterName = "loginCookieFilter",urlPatterns = "/*")
@Slf4j
public class LoginCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("logined_cookie filter.....");
        //filtercahin의 매개변수로
        //httpservletreq,resp의 구현대상 인터페이스를 받기때문에 dofilter의 매개변수로는 servletreq,resp가 들어옴
        //그래서 http관련 설정을 하고 싶다면 다운캐스팅 해야함
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        HttpSession session = req.getSession();
        //로그인중에만 detail.jsp 가능하게 , 쿠키부터 검색
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("logined_cookie")){
                session.setAttribute("user", URLEncoder.encode(cookie.getValue(), StandardCharsets.UTF_8));
                log.info("login session 생성");
            }
        }
        chain.doFilter(request,response);
    }
}
