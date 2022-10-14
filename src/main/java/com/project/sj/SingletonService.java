package com.project.sj;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.sj.config.AppConfig;
import com.project.sj.repository.JdbcUserRepository;
import com.project.sj.repository.UserRepository;
import com.project.sj.service.UserService;
import com.project.sj.service.UserServiceImpl;

public class SingletonService {
	private static SingletonService instance = new SingletonService();
	private AppConfig appConfig;
	
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
}
