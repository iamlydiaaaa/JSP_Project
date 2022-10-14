package com.project.sj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class junit_test {
	@Test
	public void test_test() throws IOException, SQLException {
		DataSource ds = dd();
		String sql = "select * from reservation";
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("title"));
		}
		log.info("log.info test");
	}
	
	public Connection getConnection() {
		final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "movie_db";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, "root", "root");
			if (conn != null) {
				System.out.println("DB 접속 성공");
				return conn;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		return null;
	}

	public DataSource dd() throws IOException {
	    String CLASSNAME = "org.mariadb.jdbc.Driver";
	    String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_db";
	    String USERNAME = "root";
	    String PASSWORD = "root";
	    String CACHE_PREP_STMTS="true";
	    
	    HikariDataSource ds;
	    HikariConfig config;

	    /* HikariCP 로드 */
		
    	config = new HikariConfig();
	    
	    config.setDriverClassName(CLASSNAME);
		config.setJdbcUrl( JDBC_URL );
		config.setUsername( USERNAME );
		config.setPassword( PASSWORD );
		config.addDataSourceProperty( "cachePrepStmts" , CACHE_PREP_STMTS );
		config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		ds = new HikariDataSource( config );

		System.out.println("성공:" + ds);
		return ds;
	}
}
