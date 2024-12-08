package com.sohamsg.assignments.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackages = {"com.sohamsg.assignments.domain.entity"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient

public class EmployeeMngmentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMngmentBackendApplication.class, args);
	}
	
	@Bean
	@LoadBalanced

	public RestTemplate loadBalanced() {
		return new RestTemplate();
	}
}
