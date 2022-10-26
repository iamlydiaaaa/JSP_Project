package com.example.reservation.controller;

import com.example.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

@WebServlet(name="reservationController",value="/reservation")
@Slf4j
public class ReservationController extends HttpServlet {

    // **************************예약가능인원수,예약가능날짜,중복시간체크는 컨트롤러에서 검증해야함**********************************

    ReservationService reservationService = SINGLETON_UTIL.reservationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReservationController.doPost");
//        try {
//            HttpSession session = req.getSession();
//            String res_dt = req.getParameter("res_dt"); //2022-10-29
//            //세션으로 id
//            String id = (String) session.getAttribute("user");
//            //세션으로 culture 받고 지우기
//            CultureVO culture = (CultureVO) session.getAttribute("culture");
//            session.removeAttribute("culture");
//            reservationService.reservation(culture,id,res_dt);
//        } catch (IllegalStateException e){
//            e.printStackTrace();
//            String msg = URLEncoder.encode("잘못된 값을 입력하셨습니다", StandardCharsets.UTF_8);
//            resp.sendRedirect("/project/list?msg="+msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//            String msg = URLEncoder.encode("다시 시도해 주세요", StandardCharsets.UTF_8);
//            resp.sendRedirect("/project/list?msg="+msg);
//        }
    }
}
