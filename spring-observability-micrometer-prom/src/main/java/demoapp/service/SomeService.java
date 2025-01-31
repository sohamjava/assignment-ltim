package demoapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brave.Span;
import brave.Tracer;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.tracing.Tracer.SpanInScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SomeService {
	private final MeterRegistry  meterRegistry;
	private final RestTemplate restTemplate;
	private final Tracer tracer;
	@Timed(value = "timed.doSomething", description = "doSomething")
	@Counted(value = "counter.doSomething")

	public String doSomething(int x) {
		Span span = tracer.nextSpan().name("doSomething").start();
		  try (brave.Tracer.SpanInScope ws = tracer.withSpanInScope(span.start())) {
				log.info("Received :: {}",x);
		
		ResponseEntity<String> y=restTemplate.getForEntity("http://localhost:8787/app/a?p1=s", String.class);
		if(x %3 ==0) {
			throw new RuntimeException("Simulated exception -/ by 3");
		}
		
		meterRegistry.counter("success.count").increment();
		return y.getBody()+":: Thanks for enrolling "+x;
		}finally {
			span.finish();
			log.info("End span");
		}
	}
	
}
