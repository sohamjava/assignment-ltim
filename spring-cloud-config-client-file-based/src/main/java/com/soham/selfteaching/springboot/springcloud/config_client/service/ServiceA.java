package com.soham.selfteaching.springboot.springcloud.config_client.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.time.StopWatch;
import org.apache.hc.client5.http.impl.Operations.CompletedFuture;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceA {

	
	public LocalDateTime someLongRunningTask1() {
		StopWatch s=new StopWatch();
		s.start();
		log.info("someLongRunningTask1 :: starts");
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.stop();
		log.info("someLongRunningTask1 :: ends in {} ms"+s.getTime());
		return LocalDateTime.now();
	}
	
	
	public LocalDateTime someLongRunningTask2() {
		StopWatch s=new StopWatch();
		s.start();
		log.info("someLongRunningTask22 :: starts");
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.stop();
		log.info("someLongRunningTask2 :: ends in {} ms"+s.getTime());
		return LocalDateTime.now();
	}
	
	
	public DeferredResult<Duration> combinedTask2() {
		DeferredResult<Duration> result=new DeferredResult<>();
		CompletableFuture<LocalDateTime> supplyAsync1 = CompletableFuture.supplyAsync(()->someLongRunningTask1());
		CompletableFuture<LocalDateTime> supplyAsync2 = CompletableFuture.supplyAsync(()->someLongRunningTask2());
		CompletableFuture.allOf(supplyAsync1,supplyAsync2).join();
		try {
			  result.setResult(Duration.between(supplyAsync1.get(), supplyAsync2.get()));
			  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return result;
	}
	@Async
	public CompletableFuture<Duration> combinedTask() {
		
		CompletableFuture<LocalDateTime> supplyAsync1 = CompletableFuture.supplyAsync(()->someLongRunningTask1());
		CompletableFuture<LocalDateTime> supplyAsync2 = CompletableFuture.supplyAsync(()->someLongRunningTask2());
		CompletableFuture.allOf(supplyAsync1,supplyAsync2).join();
		try {
			 return CompletableFuture.completedFuture(Duration.between(supplyAsync1.get(), supplyAsync2.get()));
			  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	static AtomicInteger failSimulationCount=new AtomicInteger();
	@Retryable(maxAttempts = 4)
	public String someFailingService() {
		log.info(">>>>> someFailingService >>>> called "+failSimulationCount.get());
		int x=failSimulationCount.getAndIncrement();
		if(x>0 && x%4==0) {
			try {
				TimeUnit.SECONDS.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				failSimulationCount.set(0);
			}
			return "Got through "+x;
		}else {
			throw new RuntimeException("Failed at "+x);
		}
		
	}
	@Recover()
	public String handleAttemptsExhausted(RuntimeException e) {
		return "fallback for "+e.getCause();
	}
}
