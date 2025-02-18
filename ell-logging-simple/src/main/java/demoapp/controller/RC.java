package demoapp.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demoapp.service.SomeService;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;

//import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@Slf4j()
public class RC {
	  @Autowired
	    private Tracer tracer;
	private final SomeService service;
	
	org.slf4j.Logger logger=LoggerFactory.getLogger(getClass());
	
	@GetMapping("/hello")
	public String hello(@RequestParam("name")String name) {
//		log.info("invoked method ::: Received : {}",name);
//		log.debug("Hello World : {}",name);

		logger.info("invoked method ::: Received : {}",name);
		someMethod();
		service.someMethod();
		logger.debug("Hello World : {}",name);
		
	
		return "Hello "+name;
	}
	
	@GetMapping("/ping")
	public String ping() {
		logger.info("ping-------");
		return "Hello Dear";
	}
	@NewSpan(name = "SomeAction")
	public void someMethod() {
		logger.info("first span");
	}
}
