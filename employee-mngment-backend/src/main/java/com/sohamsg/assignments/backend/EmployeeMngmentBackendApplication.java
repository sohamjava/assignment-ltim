package com.sohamsg.assignments.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EntityScan(basePackages = {"com.sohamsg.assignments.domain.entity"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EmployeeMngmentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMngmentBackendApplication.class, args);
	}

}
