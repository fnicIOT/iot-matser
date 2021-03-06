package com.fnic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableConfigurationProperties
//@MapperScan(basePackages="com.fnic.mybatis.thingsboard")
public class BootApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(BootApplication.class, args);
	}
}
