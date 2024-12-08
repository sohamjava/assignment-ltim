package com.sohamsg.assignments.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RC_Test {

	
	private final RestTemplate rt;
	@GetMapping("/test")
	public String callService() {
		return rt.getForObject("http://OS-API-ORDER-SHIPMENT/rt", String.class);
	}
}
