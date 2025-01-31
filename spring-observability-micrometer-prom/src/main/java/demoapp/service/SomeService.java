package demoapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SomeService {
	private final MeterRegistry  meterRegistry;
	private final RestTemplate restTemplate;
	@Timed(value = "timed.doSomething", description = "doSomething")
	@Counted(value = "counter.doSomething")

	public String doSomething(int x) {
		log.info("Received :: {}",x);
		
		ResponseEntity<String> y=restTemplate.getForEntity("http://localhost:8787/app/a?p1=s", String.class);
		if(x %3 ==0) {
			throw new RuntimeException("Simulated exception -/ by 3");
		}
		meterRegistry.counter("success.count").increment();
		return y.getBody()+":: Thanks for enrolling "+x;
	}
	
}
