package com.thewangzl.sh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class HsqldbConfig {

	@Bean(name="hsqlDataSource")
	@ConfigurationProperties(prefix="spring.datasource.hsql")
	public DataSource dataSource() {
		return new DriverManagerDataSource(); 
	}
	
	@Bean(name="hsqlJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("hsqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}
