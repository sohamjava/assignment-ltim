package demoapp.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brave.http.HttpServerRequest;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},allowCredentials = "true",allowPrivateNetwork = "false",origins = "google.com")
@RequestMapping("/app")
@Tag(name = "Hello Controller",description = "This is sample description")
@Slf4j
@Observed
public class SimpleRC {

	@GetMapping("/a")
	@Operation(description = "This is   a hello app")
	@Parameters(value= {
			@Parameter(name = "p1", description = "Some good param",in = ParameterIn.QUERY , content = {@Content(schema = @Schema(implementation = String.class))})
	})
	public String someApi(@RequestParam("p1")  String p,HttpServletRequest request) {
		request.getHeaderNames().asIterator().forEachRemaining(h->log.info("{} = {}",h,request.getHeader(h)));
		log.info("start method::someApi");
		someOtherMethod();
		return "Hello at "+LocalDateTime.now();
	}

	private void someOtherMethod() {
		
		log.info("start method:: someOtherMethod");
	}
}
