package com.example.reservation.controller;

import com.example.reservation.vo.ReservationVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name="reservationController",value="/reservation")
@Slf4j
public class ReservationResController extends ReservationController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("ReservationController.doGet");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date resDate = df.parse
                    (req.getParameter("sel_y") + "/" +
                            req.getParameter("sel_m") + "/" +
                            req.getParameter("sel_d"));
            Integer resCnt = Integer.valueOf(req.getParameter("useNum"));
            Long cno = Long.valueOf(req.getParameter("cno"));
            validateUser(req,req.getSession());
            String id = getLoginedId(req);
            ReservationVO reservationVO = ReservationVO
                    .builder()
                    .resDate(resDate)
                    .resCnt(resCnt)
                    .resPrice(cultureService.getCulture(cno).getPrice()*resCnt)
                    .cno(cno)
                    .id(id)
                    .build();
            /////////////////////////////////
            req.setAttribute("reservationVO",reservationVO);
            req.setAttribute("id",reservationVO.getId());
            req.setAttribute("resDate",reservationVO.getResDate());
            req.setAttribute("resCnt",reservationVO.getResCnt());
            req.setAttribute("cno",reservationVO.getCno());
            req.setAttribute("resPrice",reservationVO.getResPrice());
            ///////////////////////////////////
            req.setAttribute("page",req.getParameter("page"));
            req.setAttribute("reservationVO",reservationVO);
            req.setAttribute("userInfo",userService.getUser(id).orElseThrow());
            req.setAttribute("price",cultureService.getCulture(cno).getPrice());
            req.getRequestDispatcher("WEB-INF/view/reservation/insertReservation.jsp").forward(req,resp);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("잘못된 요청");
            resp.setStatus(400);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("예약 오류");
            throw new RuntimeException("예약 오류");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("ReservationController.doPost");
            SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
            Date resDate = recvSimpleFormat.parse(req.getParameter("resDate"));
            ReservationVO reservationVO = ReservationVO
                    .builder()
                    .id(req.getParameter("id"))
                    .resDate(resDate)
                    .resCnt(Integer.valueOf(req.getParameter("resCnt")))
                    .cno(Long.valueOf(req.getParameter("cno")))
                    .resPrice(Integer.valueOf(req.getParameter("resPrice")))
                    .build();
            System.out.println("reservationVO = " + reservationVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("예약 오류");
            throw new RuntimeException("예약 오류");
        }
    }
}
