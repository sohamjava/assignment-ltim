package demoapp.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import io.swagger.v3.oas.models.headers.Header.StyleEnum;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration

public class SecurityConfig {

	@Bean
	public WebSecurityCustomizer customizer() {
		return web -> web.ignoring()
				.requestMatchers("/**");
//				.requestMatchers("/swagger-ui/**", "/v3/api-docs*/**");
	}

	 @Bean
	    public GroupedOpenApi publicApi() {
	        return GroupedOpenApi.builder()
	                .group("v1")
	                .pathsToMatch("/**")
	                .addOperationCustomizer((op,hm)->{
	                	var p=new Parameter();
	                	p.setName("access_token");
	                	p.setAllowEmptyValue(false);
	                	p.setRequired(true);
	                	p.setDescription("Acccess Token");
	                	p.setIn("header");
	                	 op.getParameters().add(p);
	                	 return op;
	                })
	                .build();
	    }

}
