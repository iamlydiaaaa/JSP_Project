package com.project.sj.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.sj.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcUserRepository implements UserRepository{
	
	DataSource ds;
	
	public JdbcUserRepository(DataSource ds) {
		super();
		this.ds = ds;
	}

	@Override
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into user "
					+ "(id,pwd,name,email,phone,age,gender,regDate)"
					+ " values(?,?,?,?,?,?,?,?) ";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				log.error("회원가입에 실패했습니");
				e.printStackTrace();
			}			
		}
		
	}
	
}
