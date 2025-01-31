package demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import brave.Tracing;
import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;

@SpringBootApplication
public class SpringObservabilityMicrometerPromApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringObservabilityMicrometerPromApplication.class, args);
	}
	
	@Bean
	public HttpTracing create(Tracing tracing) {
	    return HttpTracing
	        .newBuilder(tracing)
	        .build();
	}

	@Bean
	public RestTemplate restTemplate(HttpTracing httpTracing) {
	    return new RestTemplateBuilder()           
	            .interceptors(TracingClientHttpRequestInterceptor.create(httpTracing))
	        .build();
	}
	 

}
