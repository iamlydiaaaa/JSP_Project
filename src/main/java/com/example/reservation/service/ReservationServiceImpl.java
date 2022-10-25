package com.example.reservation.service;

import com.example.common.util.MyTransactional;
import com.example.reservation.dao.ReservationDAO;
import com.example.reservation.vo.ReservationVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;

@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationDAO reservationDAO;

//    @Override
//    public void reservation(CultureVO culture, String id, String res_dt) throws IllegalStateException {
//        try {
//            ////////////////////////////////////////////////넘어온 예약정보 검증
//            //1.예약 신청 가능날짜 (cno로 예매가능날짜 가져옴)
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            Date from = df.parse(culture.getRcpt_bgn_dt());
//            Date to = df.parse(culture.getRcpt_end_dt());
//            Date res = df.parse(res_dt);
//
//            //2.잘못된 예약 날짜 선택
//            if(res.before(from)||res.after(to)){
//                throw new IllegalStateException("잘못된 날짜 예약");
//            }
//            //3.같은예약(내 예약의 cno가 같거나 svc_nm이 같으면) 신청시 신청불가(미구현)
//
//            //4.예약 검증(해당 cno예약 수>culture.capacity 예약불가)(미구현)
//
//            //4 유료결제(미구현)
//            // reservation_basic의 res_price에 ++)
//            //////////////////////////////////////////////////검증완료
//
//            // 검증을 통과하면 reservation 작성후 insert
//            //  예약에 성공하면 다시 상세페이지로(msg)
//            ReservationVO reservation = ReservationVO.builder()
//                    .id(id)
//                    .build();
//            reservationDAO.insert(reservation);
//
//            //컨트롤러로 검증 예외를 던짐(이게맞나)
//        } catch (IllegalStateException e) {
//            throw new IllegalStateException(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    @MyTransactional
    public Long reservation(String id, Long cno, Date resDate) {
        //넘어온 매개변수는 검증되었음
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
            conn.commit();
            conn.setAutoCommit(true);
            ///////////////////////////
            return rno;
        } catch (SQLException e) {
            try {
                ////////////////////////
                conn.rollback();
                ////////////////////////
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("롤백 도중 예외가 발생했습니다");
            }
            e.printStackTrace();
            throw new RuntimeException("롤백합니다");
        } finally {
            CONN_UTIL.close(conn);
        }
    }
}
