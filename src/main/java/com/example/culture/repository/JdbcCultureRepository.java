package com.example.culture.repository;

import com.example.domain.Culture;
import com.example.config.SingletonProvider;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCultureRepository implements CultureRepository<Culture> {
    private static Long cno = 1L;
    private final DataSource ds;
    SingletonProvider instance = SingletonProvider.getInstance();

    public JdbcCultureRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void insert(Culture culture) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into culture_basic " +
                    "(svc_nm,area_nm,place_nm,tel_no,cno) " +
                    "values(?,?,?,?,?)";
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,culture.getSvc_nm());
            pstmt.setString(2,culture.getArea_nm());
            pstmt.setString(3,culture.getPlace_nm());
            pstmt.setString(4,culture.getTel_no());
            pstmt.setLong(5,cno);
            pstmt.executeUpdate();

            sql = "insert into culture_info " +
                    "(pay_ay_nm,use_tgt_info,svc_url,img_url,dtlcont,cno) " +
                    "values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,culture.getPay_ay_nm());
            pstmt.setString(2,culture.getUse_tgt_info());
            pstmt.setString(3,culture.getSvc_url());
            pstmt.setString(4,culture.getImg_url());
            pstmt.setString(5,culture.getDtlcont());
            pstmt.setLong(6,cno);
            pstmt.executeUpdate();

            sql = "insert into culture_res " +
                    "(capacity,price,cno,revstd_day_nm,revstd_day) " +
                    "values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,culture.getCapacity());
            pstmt.setInt(2,culture.getPrice());
            pstmt.setLong(3,cno);
            pstmt.setString(4,culture.getRevstd_day_nm());
            pstmt.setString(5,culture.getRevstd_day());
            pstmt.executeUpdate();

            sql = "insert into culture_schedule " +
                    "(svc_opn_bgn_dt,svc_opn_end_dt,v_min,v_max,rcpt_bgn_dt,rcpt_end_dt,cno) " +
                    "values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,culture.getSvc_opn_bgn_dt());
            pstmt.setString(2,culture.getSvc_opn_end_dt());
            pstmt.setString(3,culture.getV_min());
            pstmt.setString(4,culture.getV_max());
            pstmt.setString(5,culture.getRcpt_bgn_dt());
            pstmt.setString(6,culture.getRcpt_end_dt());
            pstmt.setLong(7,cno);
            pstmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            cno++;
        } catch (SQLException e) {
            instance.rollback(conn);
            e.printStackTrace();
            System.out.println("cultrue 등록에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            instance.close(pstmt);
            instance.close(conn);
        }
    }

    //작성중
    @Override
    public PageResponse<Culture> select(PageRequest pageRequest) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageResponse<Culture> pageResponse = null;
        try {
            String sql = "select dtlcont from culture_info";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Culture> cultures = new ArrayList<>();


             pageResponse = PageResponse.<Culture>withAll()
            .pageRequestDTO(pageRequest)
//            .total(Culture.getTotal(pageRequest))
            .pageList(cultures)
            .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            instance.close(rs);
            instance.close(pstmt);
            instance.close(conn);
        }
        return pageResponse;
    }

    @Override
    public String test() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "";
        try {
            String sql = "select dtlcont from culture_info where cno=?";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,1);
            rs = pstmt.executeQuery();
            rs.next();
            result = rs.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            instance.close(rs);
            instance.close(pstmt);
            instance.close(conn);
        }
        return result;
    }
}
