package com.example.config;



import com.example.api.ApiProvider;
import com.example.api.CultureJsonApiProvider;
import com.example.api.ProjectApiRatePolicy;
import com.example.domain.Culture;
import com.example.culture.repository.JdbcCultureRepository;
import com.example.user.repository.JdbcUserRepository;
import com.example.culture.repository.RegisterRepository;
import com.example.user.repository.UserRepository;
import com.example.culture.service.CultureServiceImpl;
import com.example.culture.service.CultureService;
import com.example.user.service.UserService;
import com.example.user.service.UserServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppConfig {

    private final DataSource ds;

    public AppConfig() {
        this.ds = dataSource();
    }

    ///////////////////////user

    //userService
    public UserService userService() {
        return new UserServiceImpl(
                userRepository(ds));
    }

    //userRepository
    public UserRepository userRepository(DataSource ds) {
        return new JdbcUserRepository(ds);
    }

    ///////////////////////culture

    //cultureService
    public CultureService registerService(){
        ApiProvider apiProvider = new CultureJsonApiProvider
                ("6653645678736b6139317441527257","문화행사");
            return new CultureServiceImpl(
                    apiProvider,new ProjectApiRatePolicy()
                    ,new JdbcCultureRepository(ds));
    }

    //cultureRepository
    public RegisterRepository<Culture> registerRepository() {
        return new JdbcCultureRepository(ds);
    }



    //dataSource
    private DataSource dataSource() {
        return new Hikari_ds().config();
    }

    //getConnection
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            System.out.println("db커넥션을 얻어오지 못했습니다.");
            e.printStackTrace();
        }
        return null;
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
