package com.example.repository;

import com.example.domain.Culture;
import com.example.config.SingletonProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcCultureRepository implements RegisterRepository<Culture>{
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
}
