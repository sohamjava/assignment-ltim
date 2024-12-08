package com.soham.selfteaching.springboot.springcloud.config_client.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@ConfigurationProperties("app.shipment")
@Configuration(proxyBeanMethods = false)
@Data
@ToString(includeFieldNames = true)

public class AppConfig {
	private Map<String, Object> routing = new HashMap<>();
	private int pltDelay;
	private String secret;
}
