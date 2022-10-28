package com.example.reservation.controller;

import com.example.culture.service.CultureService;
import com.example.qna.vo.QnA_Q_VO;
import com.example.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

@Slf4j
public class ReservationController extends HttpServlet {

    protected ReservationService reservationService = SINGLETON_UTIL.reservationService();

    protected CultureService cultureService = SINGLETON_UTIL.cultureService();

    protected void validateUser(HttpServletRequest req, HttpSession session, QnA_Q_VO qnaQ) {
        String paramId = qnaQ.getId();
        String loginedCookie = getLoginedCookie(req).getValue();
        String sessionUser = (String) session.getAttribute("user");
        if(loginedCookie.equals("null")&&sessionUser==null){
            log.error("비로그인 에러");
            throw new IllegalStateException("먼저 로그인을 해주세요");
        }
        if(sessionUser!=null&&!sessionUser.equals(paramId)) {
            log.error("session id 불일치");
            throw new IllegalStateException("작성자만 수정할 수 있습니다");
        }
        if(!loginedCookie.equals("null")&&!loginedCookie.equals(paramId)){
            log.error("logined_cookie id 불일치");
            throw new IllegalStateException("작성자만 수정할 수 있습니다");
        }
    }

    protected void validateUser(HttpServletRequest req, HttpSession session) {
        String loginedCookie = getLoginedCookie(req).getValue();
        String sessionUser = (String) session.getAttribute("user");
        if(loginedCookie.equals("null")&&sessionUser==null){
            log.error("비로그인 에러");
            throw new IllegalStateException("먼저 로그인을 해주세요");
        }
    }

    protected Cookie getLoginedCookie(HttpServletRequest req) {
        Cookie logined_cookie = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("logined_cookie")){
                logined_cookie=cookie;
            }
        }
        if(logined_cookie==null){
            logined_cookie=new Cookie("temp","null");
        }
        return logined_cookie;
    }
}
