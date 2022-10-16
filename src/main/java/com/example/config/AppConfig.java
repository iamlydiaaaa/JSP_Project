package com.example.config;



import com.example.api.ApiProvider;
import com.example.api.CultureJsonApiProvider;
import com.example.api.ProjectApiRatePolicy;
import com.example.domain.Culture;
import com.example.repository.JdbcCultureRepository;
import com.example.repository.JdbcUserRepository;
import com.example.repository.RegisterRepository;
import com.example.repository.UserRepository;
import com.example.service.CultureServiceImpl;
import com.example.service.CultureService;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppConfig {

    ///////////////////////user

    //userService
    public UserService userService() {
        return new UserServiceImpl(
                userRepository(dataSource()));
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
                    ,new JdbcCultureRepository(dataSource()));
    }

    //cultureRepository
    public RegisterRepository<Culture> registerRepository() {
        return new JdbcCultureRepository(dataSource());
    }



    //dataSource
    private DataSource dataSource() {
        return new Hikari_ds().config();
    }

    //getConnection
    public Connection getConnection() {
        try {
            return dataSource().getConnection();
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
