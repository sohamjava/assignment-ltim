package demoapp.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demoapp.service.SomeService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/app")
@Slf4j
@RequiredArgsConstructor

public class SimpleRestController {

	private final SomeService service;
	private final MeterRegistry  meterRegistry;
	private AtomicInteger ai=new AtomicInteger(0);

	@GetMapping("/chk")
	public String someEndPoint(@RequestParam("x") int x) {
		log.info("Received : {}",x);
		meterRegistry.gauge("my_input_num",ai,AtomicInteger::get).set(x);
		aCounter(x).increment();
		return service.doSomething(x);
	}
	
	public Counter aCounter(int x) {
		 Counter counter = Counter.builder("api_input_number")
		            .tag("title", x%2==0 ? "Even" : "Odd")
		            .description("a number of requests to /api/books endpoint")
		            .register(meterRegistry);
		 return counter;
	}
}
