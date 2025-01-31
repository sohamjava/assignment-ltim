package demoapp.controller.exceptionhandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice()

@Slf4j
@RequiredArgsConstructor
public class SimpleExceptionHandler {
	private final MeterRegistry  meterRegistry;
	
	@ExceptionHandler(exception = {RuntimeException.class})
	public void handleException(Exception e) {
		log.error("Some error happened :",e);
		meterRegistry.counter("failure.count").increment();
	}
}
