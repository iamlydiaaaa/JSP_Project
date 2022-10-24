package com.example.review.dao;

import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.common.ConnectionUtil.CONN_UTIL;

public class JdbcReviewDAO implements ReviewDAO {


    @Override
    public Long insert(ReviewVO reviewVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into review (id, cno, content, grade)\n" +
                    "values (?,?,?,?)";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reviewVO.getId());
            pstmt.setLong(2, reviewVO.getCno());
            pstmt.setString(3, reviewVO.getContent());
            pstmt.setInt(4, reviewVO.getGrade());
            pstmt.executeUpdate();
            return reviewVO.getCno();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("리뷰 입력 실패");
        } finally {
            CONN_UTIL.close(pstmt, conn);
        }
    }

    @Override
    public PageResponse<ReviewVO> selectAll_byCno(Long cno,PageRequest pageRequest) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageResponse<ReviewVO> pageResponse = null;
        try {
            String sql = "select * from review \n" +
                    "         where cno = ? \n" +
                    "         order by re_no \n" +
                    "limit ?,?";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cno);
            pstmt.setInt(2, pageRequest.getSkip());
            pstmt.setInt(3, pageRequest.getSize());
            rs = pstmt.executeQuery();
            List<ReviewVO> reviewVOS = new ArrayList<>();
            while (rs.next()) {
                ReviewVO reviewVO = ReviewVO.builder()
                        .re_no(rs.getLong(1))
                        .id(rs.getString(2))
                        .cno(rs.getLong(3))
                        .content(rs.getString(4))
                        .grade(rs.getInt(5))
                        .regDate(rs.getDate(6))
                        .build();
                reviewVOS.add(reviewVO);
            }
            pageResponse = PageResponse.<ReviewVO>withAll()
                    .pageRequestDTO(pageRequest)
                    .total(selectCount(cno))
                    .pageList(reviewVOS)
                    .build();
            return pageResponse;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("review 조회에 실패했습니다");
        } finally {
            CONN_UTIL.close(rs, pstmt, conn);
        }
    }

    @Override
    public ReviewVO update(ReviewVO reviewVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "update review\n" +
                    "set content = ? , grade = ?\n" +
                    "where re_no = ?;";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,reviewVO.getContent());
            pstmt.setInt(2,reviewVO.getGrade());
            pstmt.setLong(3,reviewVO.getRe_no());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("review 업데이트에 실패했습니다");
        } finally {
            CONN_UTIL.close(pstmt,conn);
        }
        return reviewVO;
    }

    @Override
    public void delete(Long re_no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "delete\n" +
                    "from review\n" +
                    "where re_no = ?";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,re_no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("review 삭제에 실패했습니다");
        } finally {
            CONN_UTIL.close(pstmt,conn);
        }
    }

    @Override
    public int selectCount(Long cno) {
        String sql = "select count(*) from review " +
                "where cno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cno);
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("review 조회(count)에 실패했습니다");
        } finally {
            CONN_UTIL.close(rs,pstmt,conn);
        }
    }

    @Override
    public ReviewVO select(Long re_no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageResponse<ReviewVO> pageResponse = null;
        try {
            String sql = "select * from review " +
                    "where re_no = ?";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,re_no);
            rs = pstmt.executeQuery();
            rs.next();
            return ReviewVO.builder()
                    .re_no(rs.getLong(1))
                    .id(rs.getString(2))
                    .cno(rs.getLong(3))
                    .content(rs.getString(4))
                    .grade(rs.getInt(5))
                    .regDate(rs.getDate(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("review 조회에 실패했습니다");
        } finally {
            CONN_UTIL.close(rs, pstmt, conn);
        }
    }
}
