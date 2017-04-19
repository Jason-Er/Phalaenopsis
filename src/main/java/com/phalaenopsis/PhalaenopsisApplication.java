package com.phalaenopsis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.phalaenopsis.storage.StorageProperties;

import com.phalaenopsis.storage.StorageService;

@SpringBootApplication(scanBasePackages = { "com.phalaenopsis" })
@EnableConfigurationProperties(StorageProperties.class)
public class PhalaenopsisApplication {

	public static final Logger logger = LoggerFactory.getLogger(PhalaenopsisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PhalaenopsisApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
