package com.example.reservation.dao;

import com.example.reservation.vo.ReservationVO;

import java.sql.Connection;
import java.util.Date;

public interface ReservationDAO {
    void insertReservation(ReservationVO reservationVO , Connection conn);

    Long insertResCulture(Long rno, Long cno, Integer price , Date resDate , Connection conn);

    Integer selectPriceFromCulture(Long cno , Connection conn);

    Integer updateUserPaymentAmount(String id , Integer price, Connection conn);

    Long selectRno(String id,Date resDate,Connection conn);
}
