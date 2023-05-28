package com.netrunner.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@SpringBootApplication
public class PetstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}
