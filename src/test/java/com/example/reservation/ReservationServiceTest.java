package com.example.reservation;

import com.example.common.config.AppConfig;
import com.example.common.util.ConnectionUtil;
import com.example.reservation.dao.ReservationDAO;
import com.example.reservation.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;
import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

public class ReservationServiceTest {

    ReservationService reservationService = SINGLETON_UTIL.reservationService();
    ReservationDAO reservationDAO = new AppConfig().reservationDAO();

    @Test
    @DisplayName("예약 성공 케이스(유료)")
    public void reservationTest1() throws Exception {
        //given
        String id = "user1";
        Long cno = 30L; // 유료 6000 10-25 ~ 11-11
        Date resDate = java.sql.Timestamp.valueOf(LocalDate.of(2022,10,25).atStartOfDay());
        //when
        Long rno = reservationService.reservation(id,cno,resDate);
        //then
        //예약에 성공한 rno 와 selectRno로 가져온 rno는 같은 값이어야함
        Assertions.assertEquals(rno,reservationDAO.selectRno(id,resDate, CONN_UTIL.getConnection()));
        //예약에 성공한 유저의 지불금액이 cno의 요금과 같아야함
        Assertions.assertEquals(6000,reservationDAO.selectPriceFromCulture(cno,CONN_UTIL.getConnection()));
    }

    @Test
    @DisplayName("예약 성공 케이스(무료)")
    public void reservationTest2() throws Exception {
        //given

        //when

        //then
    }
}
