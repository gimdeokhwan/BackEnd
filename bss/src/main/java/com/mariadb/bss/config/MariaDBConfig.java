package com.mariadb.bss.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MariaDBConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/test");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("hwan0331");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        System.out.println("MaraiDB Connection Success!!!");
        return dataSource;
    }
}
