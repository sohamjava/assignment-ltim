package demoapp.service;

import org.springframework.stereotype.Service;

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
	
	@Timed(value = "timed.doSomething", description = "doSomething")
	@Counted(value = "counter.doSomething")

	public String doSomething(int x) {
		log.info("Received :: {}",x);
		if(x %3 ==0) {
			throw new RuntimeException("Simulated exception -/ by 3");
		}
		meterRegistry.counter("success.count").increment();
		return "Thanks for enrolling "+x;
	}
	
}
