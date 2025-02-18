package demoapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SomeService {
	
	private final RestTemplate restTemplate;

	public void someMethod() {
		log.info("Hii... 2nd span");
		
		ResponseEntity<String> r = restTemplate.getForEntity("http://localhost:8787/app/a?p1=babun",String.class);
		log.info("Here is response : {}",r.getBody());
		
	}
}
