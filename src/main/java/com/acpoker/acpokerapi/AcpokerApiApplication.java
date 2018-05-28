package com.acpoker.acpokerapi;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;

@SpringBootApplication
@EnableAutoConfiguration
public class AcpokerApiApplication {
	private static final Logger LOG = LogManager.getLogger(AcpokerApiApplication.class);

	static {
		String pid = ManagementFactory.getRuntimeMXBean().getName().replaceAll("@.*", "");
		ThreadContext.put("pid", pid);
	}

	public static void main(String[] args) {
		SpringApplication.run(AcpokerApiApplication.class, args);
	}
}
