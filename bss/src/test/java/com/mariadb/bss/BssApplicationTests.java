package com.mariadb.bss;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
class BssApplicationTests {
	@Autowired
	private DataSource dataSource;
	@Test
	public void hikariDataSourceTest() {
		try {
			Connection con = dataSource.getConnection();
			System.out.println("'Connection객체 : " + con + "'");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
