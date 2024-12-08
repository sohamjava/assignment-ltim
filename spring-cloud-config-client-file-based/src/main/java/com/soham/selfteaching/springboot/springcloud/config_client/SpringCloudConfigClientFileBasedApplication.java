package com.soham.selfteaching.springboot.springcloud.config_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync()
@EnableRetry
public class SpringCloudConfigClientFileBasedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientFileBasedApplication.class, args);
	}

	
}
