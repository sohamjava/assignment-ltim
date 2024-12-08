package com.soham.selfteaching.springboot.springcloud.config_client.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soham.selfteaching.springboot.springcloud.config_client.config.AppConfig;
import com.soham.selfteaching.springboot.springcloud.config_client.service.ServiceA;

import lombok.extern.slf4j.Slf4j;


@RestController
@RefreshScope
@Slf4j
public class RC_Chk {
	@Autowired
	AppConfig appConfig;
	@Value("${app.shipment.routing.size}")
	private String x;
	
	@Autowired
	ServiceA serviceA;
	
	@GetMapping("/check1")
	public AppConfig checkAppConfig() {
		return appConfig;
	}
	
	@GetMapping("/check2")
	public String checkAppConfig2() {
		return x;
	}
	
	@GetMapping("/rt")
	public String checkCallRestTemplate() {
		return "Welcome. Server time "+LocalDateTime.now();
	}
	
	
	@GetMapping("/async-test")
	public CompletableFuture<Duration> asyncTest() {
		log.info("asyncTest :: Called ");
		var a =serviceA.combinedTask();
		log.info("asyncTest :: ends ");
		return a;
	}
	
	@GetMapping("/retry")
	public String callSomeFailingService() {
		return serviceA.someFailingService();
	}
}
