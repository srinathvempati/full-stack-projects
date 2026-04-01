package com.srinathprojects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Product {
	
	// When i want to create object for Database config or security config we will use @Bean with @Configuration
	// Because to create Singleton Object.
	
	// private RestTemplate temlplate = new RestTemplate();   //Here we can create N number of Object but if use will method call it will create only one
	
	@Bean
     RestTemplate getTemplate() {  // do use public bcz its a single object
		System.out.println("How @Bean will work");
		return new RestTemplate();
	}

}
