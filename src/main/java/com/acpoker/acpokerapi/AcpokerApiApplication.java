package com.acpoker.acpokerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AcpokerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcpokerApiApplication.class, args);
	}
}
