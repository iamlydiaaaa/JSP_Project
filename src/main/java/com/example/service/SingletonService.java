package com.example.service;



import com.example.config.AppConfig;
import com.example.domain.Culture;
import com.example.repository.RegisterRepository;
import com.example.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    private final AppConfig appConfig;

    private SingletonService() {
        appConfig = new AppConfig();
    }

    public static SingletonService getInstance() {
        return instance;
    }

    ///////////////////user

    //userService
    public UserService userService() {
        return appConfig.userService();
    }

    //userRepository
    public UserRepository userRepository(DataSource ds) {
        return appConfig.userRepository(ds);
    }

    ///////////////////register<culture>

    //registerService
    public RegisterService registerService() {
        return appConfig.registerService();
    }

    //registerRepository
    public RegisterRepository<Culture> registerRepository() {
        return appConfig.registerRepository();
    }


    //getConnection
    public Connection getConnection() {
        return appConfig.getConnection();
    }

    //closeConnection
    public void close(ResultSet rs) {
        appConfig.close(rs);
    }

    public void close(PreparedStatement pstmt) {
        appConfig.close(pstmt);
    }

    public void close(Connection conn) {
        appConfig.close(conn);
    }

    public void rollback(Connection conn) {
        appConfig.rollback(conn);
    }
    //
}
