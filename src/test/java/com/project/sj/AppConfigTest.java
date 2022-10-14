package com.project.sj;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.project.sj.config.AppConfig;
import com.project.sj.config.Hikari_ds;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppConfigTest {
	
	AppConfig appConfig = new AppConfig();
	
	@Test
	public void db커넥션테스트() throws SQLException {
		Connection conn = appConfig.getConnection();
		log.info("conn:"+conn);
		Assert.assertNotNull(conn);
	}
}
