package com.example.qna.controller;

import com.example.qna.service.QnAService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;

import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

@Slf4j
public class QnAController extends HttpServlet {
    protected final QnAService qnAService= SINGLETON_UTIL.qnAService();

//    protected void validateUser(HttpServletRequest req, HttpSession session, String paramId) {
//        String sessionUser = (String) session.getAttribute("user");
//        if(sessionUser==null){
//            log.error("비로그인 에러");
//            throw new IllegalStateException("먼저 로그인을 해주세요");
//        }
//        if(sessionUser.equals("admin")){
//            log.info("관리자 인증");
//            return;
//        }
//        if(!sessionUser.equals(paramId)) {
//            log.error("session id 불일치");
//            throw new IllegalStateException("작성자만 수정할 수 있습니다");
//        }
//    }
//    protected void validateUser(HttpSession session) {
//        String sessionUser = (String) session.getAttribute("user");
//        if(sessionUser==null){
//            log.error("비로그인 에러");
//            throw new IllegalStateException("먼저 로그인을 해주세요");
//        }
//    }
}
