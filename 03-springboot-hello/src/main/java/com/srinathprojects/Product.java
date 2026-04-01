package com.srinathprojects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Product {
	
	@Bean
	public RestTemplate getTemplate() {
		System.out.println("How @Bean will work");
		return new RestTemplate();
	}

}
