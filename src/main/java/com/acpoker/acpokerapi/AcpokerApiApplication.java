package com.acpoker.acpokerapi;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AcpokerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcpokerApiApplication.class, args);
		String log4jConfPath = "C:\\Projects\\Spring\\acpoker-api\\src\\main\\resources\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}
}
