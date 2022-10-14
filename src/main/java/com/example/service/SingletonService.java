package com.example.service;



import com.example.config.AppConfig;
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

    //userService
    public UserService userService() {
        return appConfig.userService();
    }

    //userRepository
    public UserRepository userRepository(DataSource ds) {
        return appConfig.userRepository(ds);
    }

    //getConnection
    public Connection getConnection() {
        return appConfig.getConnection();
    }

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
}
