package com.ht.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"com.ht.*"})
@MapperScan(basePackages = {"com.ht.mapper"})
public class RootConfig {
	  @Bean
	  public DataSource dataSource() {
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");

	    hikariConfig.setUsername("book_ex");
	    hikariConfig.setPassword("book_ex");

	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	    return dataSource;
	  }

	  @Bean
	  public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	    sqlSessionFactory.setDataSource(dataSource());
	    return (SqlSessionFactory) sqlSessionFactory.getObject();
	  }

	  @Bean
	  public DataSourceTransactionManager txManager() {
		  return new DataSourceTransactionManager(dataSource());
	  }
	}

