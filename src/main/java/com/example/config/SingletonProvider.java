package com.example.config;



import com.example.domain.Culture;
import com.example.culture.repository.RegisterRepository;
import com.example.user.repository.UserRepository;
import com.example.culture.service.CultureService;
import com.example.user.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SingletonProvider {
    private static final SingletonProvider instance = new SingletonProvider();
    private final AppConfig appConfig;

    private SingletonProvider() {
        appConfig = new AppConfig();
    }

    public static SingletonProvider getInstance() {
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

    ///////////////////culture

    //cultureService
    public CultureService registerService() {
        return appConfig.registerService();
    }

    //cultureRepository
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
