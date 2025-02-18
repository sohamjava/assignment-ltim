package demoapp.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoapp.service.SomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MyController {
	private final SomeService service;
	@GetMapping("/now")
	public String hello() {
		log.info("Hello Controller ------------------------------");
		service.someMethod();
		return "Welcome " + LocalDateTime.now();
	}

}
