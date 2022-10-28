package com.example.reservation.controller;

import com.example.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

@WebServlet(name="reservationController",value="/reservation")
@Slf4j
public class ReservationResController extends ReservationController {


    ReservationService reservationService = SINGLETON_UTIL.reservationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReservationController.doPost");

    }
}
