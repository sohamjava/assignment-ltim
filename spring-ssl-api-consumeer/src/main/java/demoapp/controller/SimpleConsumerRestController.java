package demoapp.controller;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consume")
public class SimpleConsumerRestController {
	
    RestTemplate rt=new RestTemplate();

	@GetMapping("hello")
	public ResponseEntity<String> hello(@RequestParam("x") String x) throws  Exception {
		return restTemplate().getForEntity("https://localhost:8080/api/hello?x="+x, String.class);
	}
	
	
    public RestTemplate restTemplate() throws Exception {
    	SSLContext sslContext = new SSLContextBuilder()
    			 
    	         .loadTrustMaterial(new ClassPathResource("keystore.jks").getURL(), "1234567".toCharArray()).build();
    	        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
    	        
    	        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
    	                .setSSLSocketFactory(sslConFactory)
    	                .build();
    	        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
    	        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    	        return new RestTemplate(requestFactory);
    }
}
