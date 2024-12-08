package com.soham.selfteaching.springboot.springcloud.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerFileBasedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerFileBasedApplication.class, args);
	}

}
