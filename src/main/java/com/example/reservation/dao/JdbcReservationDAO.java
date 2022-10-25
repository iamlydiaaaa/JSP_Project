package com.example.reservation.dao;

import com.example.reservation.vo.ReservationVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;

public class JdbcReservationDAO implements ReservationDAO {

    @Override
    public void insertReservation(ReservationVO reservationVO , Connection conn) {
        System.out.println("resVO = "+reservationVO);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "insert into reservation (id,resDate) values (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,reservationVO.getId());
            pstmt.setDate(2,new java.sql.Date(reservationVO.getResDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("reservation 등록에 실패했습니다");
        } finally {
            CONN_UTIL.close(pstmt);
        }
    }

    @Override
    public Long insertResCulture(Long rno, Long cno, Integer price, Date resDate , Connection conn) {
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into res_culture (rno, cno, resPrice, resDate)\n" +
                    "values (?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,rno);
            pstmt.setLong(2,cno);
            pstmt.setInt(3,price);
            pstmt.setDate(4,new java.sql.Date(resDate.getTime()));
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("insertResCultrue 등록에 실패했습니다");
        } finally {
            CONN_UTIL.close(pstmt);
        }
        return null;
    }

    @Override
    public Integer selectPriceFromCulture(Long cno , Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select price from culture_res where cno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("culture_res price 조회에 실패했습니다");
        } finally {
            CONN_UTIL.close(rs);
            CONN_UTIL.close(pstmt);
        }
        return null;
    }

    @Override
    public Integer updateUserPaymentAmount(String id , Integer price, Connection conn) {
        PreparedStatement pstmt = null;
        try {
            String sql = "update user_res\n" +
                    "set payment_amount = ?\n" +
                    "where id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,price);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
            return price;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(pstmt);
        }
    }

    @Override
    public Long selectRno(String id, Date resDate,Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select rno from reservation where id = ? and resDate = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setDate(2,new java.sql.Date(resDate.getTime()));
            rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getLong("rno");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(rs);
            CONN_UTIL.close(pstmt);
        }
        return null;
    }
}
