package com.example.reservation.service;

import com.example.common.util.MyTransactional;
import com.example.culture.dao.CultureDAO;
import com.example.culture.vo.CultureVO;
import com.example.reservation.dao.ReservationDAO;
import com.example.reservation.vo.ReservationCntVO;
import com.example.reservation.vo.ReservationCultureVO;
import com.example.reservation.vo.ReservationVO;
import com.example.user.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;

@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationDAO reservationDAO;
    private final CultureDAO<CultureVO> cultureDAO;

    private final UserDAO userDAO;

    /**
     * 예약하기
     */
    @Override
    @MyTransactional
    public Long reservation(String id, Long cno, Date resDate) {
        //validation 메서드 만들어서 실행
        Connection conn = CONN_UTIL.getConnection();
        try {
            //////////////////////////
            conn.setAutoCommit(false);
            //////////////////////////
            //선택한 cno의 요금 조회
            Integer resPrice = reservationDAO.selectPriceFromCulture(cno,conn);
            if(resPrice==null||resPrice<0) {
                throw new SQLException("cultrue_res price 조회에 실패하여, reservation이 실패했습니다");
            }
            //reservation insert
            reservationDAO.insertReservation(
                    ReservationVO
                            .builder().id(id).resDate(resDate).build(),conn);
            //id,resDate 그룹은 중복될수 없으니 고유한 rno 조회 가능
            Long rno = reservationDAO.selectRno(id,resDate,conn);
            if(rno==null) {
                throw new SQLException("reservation rno 조회에 실패하여, reservation이 실패했습니다");
            }
            //res_culture insert
            reservationDAO.insertResCulture(rno,cno,resPrice,resDate,conn);
            // 유료일 경우 유저의 payment_amount 업데이트
            if(resPrice>0){
                Integer userPrice = getPayment_amount(id);
                userPrice += resPrice;
                if(!reservationDAO.updateUserPaymentAmount(id,userPrice,conn)){
                    throw new SQLException("유저 요금 업데이트 실패");
                }
            }
            ///////////////////////////
            conn.commit();
            conn.setAutoCommit(true);
            ///////////////////////////
            return rno;
        } catch (Exception e) {
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
    }//reservation

    /**
     * 고객의 예약 리스트 조회
     */
    @Override
    @MyTransactional
    public List<ReservationVO> getReservationsById(String id) {
        Connection conn = CONN_UTIL.getConnection();
        List<ReservationVO> reservationList = new ArrayList<>(); //resprice,resdate,regdate
        try {
            //////////////////////////
            conn.setAutoCommit(false);
            //////////////////////////
            //예약조회 (
            //id에 해당하는 rno list 가져온후
            List<Long> rnoList = reservationDAO.selectAllRnoById(id,conn);
            if(rnoList.isEmpty()){
                throw new SQLException("rnoList를 조회하지 못했습니다");
            }

            //id의 rno(예약)의 개수만큼 foreach를 순회하면서 reservationVO list를 초기화
            rnoList.stream().forEach(rno -> {
                ReservationCultureVO reservationCultureVO = reservationDAO.selectResCultureByRno(rno, conn);
                CultureVO cultureVO = cultureDAO.selectOne(reservationCultureVO.getCno());
                ReservationVO reservationVO = ReservationVO
                        .builder()
                        .rno(rno)
                        .id(id)
                        .resDate(reservationCultureVO.getResDate())
                        .resPrice(reservationCultureVO.getResPrice())
                        .regDate(reservationCultureVO.getRegDate())
                        .culture(cultureVO)
                        .build();
                reservationList.add(reservationVO);
            });
            ///////////////////////////
            conn.commit();
            conn.setAutoCommit(true);
            ///////////////////////////
            return reservationList;
        } catch (SQLException e) {
            try {
                ////////////////////////
                conn.rollback();
                ////////////////////////
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("롤백 도중 예외가 발생했습니다");
            }
            e.printStackTrace();
            throw new RuntimeException("롤백합니다");
        } finally {
            CONN_UTIL.close(conn);
        }
    }//getReservationsById

    /**
     * 행사 진행일당 예약한인원수,예약정원 조회
     */
    @Override
    public Map<Long,ReservationCntVO> getReservationCnt(Long cno) {
        try {
//            //1.cno,resDate를 group by 후 count (현재 예약자수)
//            List<Integer> currentResCnts = reservationDAO.selectGroupResCulture(cno);
//            //2.(총 예약 리미트)
//            Integer capacity = reservationDAO.selectCultureResCapacity(cno);
//            if(currentResCnts.isEmpty()||capacity==null) {
//                throw new IllegalStateException("예약한 인원수 조회에 실패했습니다");
//            }
//            return ReservationCntVO
//                    .builder()
//                    .currentResCnts(currentResCnts)
//                    .capacity(capacity)
//                    .build();
            return reservationDAO.selectReservationCnt(cno);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }//getReservationCnt

    /**
     * 예약 취소
     */
    @Override
    @MyTransactional
    public boolean cancelReservation(String id,Long rno) {
        Connection conn = CONN_UTIL.getConnection();

        try {
            //////////////////////////
            conn.setAutoCommit(false);
            //////////////////////////
            //결제금액 취소를 위한 resPrice
            Integer resPrice = reservationDAO.selectResCultureByRno(rno,conn).getResPrice();
            //유저의 결제 업데이트
            if(resPrice<0) {
                throw new SQLException("resPrice 조회 실패");
            } else if(resPrice>0) {
                Integer userPrice = getPayment_amount(id);
                userPrice -= resPrice;
                if(!reservationDAO.updateUserPaymentAmount(id,userPrice,conn)){
                    throw new SQLException("유저 요금 업데이트 실패");
                }
            }
            //res_culture delete
            reservationDAO.deleteResCulture(rno,conn);
            //reservation delete
            reservationDAO.deleteReservation(rno,conn);

            ///////////////////////////
            conn.commit();
            conn.setAutoCommit(true);
            ///////////////////////////
            return true;
        } catch (Exception e) {
            try {
                ////////////////////////
                conn.rollback();
                ////////////////////////
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("롤백 도중 예외가 발생했습니다");
            }
            e.printStackTrace();
            throw new RuntimeException("롤백합니다");
        } finally {
            CONN_UTIL.close(conn);
        }
    }//cancelReservation

    private Integer getPayment_amount(String id) {
        return userDAO.getById(id).orElseThrow().getPayment_amount();
    }
}
