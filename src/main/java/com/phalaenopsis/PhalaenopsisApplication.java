package com.phalaenopsis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.phalaenopsis"})
public class PhalaenopsisApplication {

	public static final Logger logger = LoggerFactory.getLogger(PhalaenopsisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PhalaenopsisApplication.class, args);
	}
}
