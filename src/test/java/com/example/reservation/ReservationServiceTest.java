package com.example.reservation;

import com.example.AppConfig;
import com.example.reservation.dao.ReservationDAO;
import com.example.reservation.service.ReservationService;
import com.example.reservation.vo.ReservationCntVO;
import com.example.reservation.vo.ReservationVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;
import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        //when,then
        assertTrue(copied_reservation(id,cno,resDate));
//        reservationService.reservation(id,cno,resDate);
    }

    @Test
    @DisplayName("예약 성공 케이스(무료)")
    public void reservationTest2() throws Exception {
        //given
        String id = "user1";
        Long cno = 31L; // 무료 0 10-25 ~ 11-18
        Date resDate = java.sql.Timestamp.valueOf(LocalDate.of(2022,10,25).atStartOfDay());
        //when,then
        assertTrue(copied_reservation(id,cno,resDate));
//        reservationService.reservation(id,cno,resDate);
    }

    // 예약실패 테스트 해야함
    // 예약가능인원수,예약가능날짜
    // 중복cno,중복시간체크

    @Test
    @DisplayName("고객 예약 조회")
    public void getReservationByIdTest() throws Exception {
        //given
        String id = "user1";
        //when
        List<ReservationVO> reservations = reservationService.getReservationsById(id);
        System.out.println("reservations = " + reservations);
        //then
        assertTrue(reservations.size()>0);
    }

    @Test
    @DisplayName("고객 예약 취소")
    public void cancelReservationTest(){
        //given
        String id = "user1";
        Long rno = 23L;
        //when , then
        assertTrue(reservationService.cancelReservation(id,rno));
    }

    @Test
    @DisplayName("일자별 예약 가능인원 정보 조회")
    public void getReservationCntTest() throws Exception {
        //given
        Long cno = 30L;
//        Long cno = 31L;
        //when
        Map<Long, ReservationCntVO> resCntMap = reservationDAO.selectReservationCnt(cno);
        //then
        assertTrue(resCntMap.size()>0);
        System.out.println("resCntMap = " + resCntMap);
    }

    public boolean copied_reservation(String id, Long cno, Date resDate) {
        Connection conn = CONN_UTIL.getConnection();
        try {
            //////////////////////////
            conn.setAutoCommit(false);
            //////////////////////////

            Integer price = reservationDAO.selectPriceFromCulture(cno,conn);
            if(price==null||price<0) {
                throw new SQLException("cultrue_res price 조회에 실패하여, reservation이 실패했습니다");
            }
            //reservation insert ->(return rno)
            reservationDAO.insertReservation(
                    ReservationVO
                            .builder().id(id).resDate(resDate).build(),conn);
            //id,resDate 그룹은 중복될수 없음
            Long rno = reservationDAO.selectRno(id,resDate,conn);
            System.out.println("rno = " + rno);
            reservationDAO.insertResCulture(rno,cno,price,resDate,conn);
            if(price>0){ // 유료일 경우 유저의 payment_amount 업데이트
                reservationDAO.updateUserPaymentAmount(id,price,conn);
            }

            ///////////////////////////
//            conn.commit();
            conn.rollback();
            conn.setAutoCommit(true);
            ///////////////////////////
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            CONN_UTIL.close(conn);
        }
    }
}
