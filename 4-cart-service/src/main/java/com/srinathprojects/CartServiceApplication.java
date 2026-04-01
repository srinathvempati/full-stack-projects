package com.srinathprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}
	
	// This is for the Distributed tracing(Micro Meter)
	
	@Bean
	MicrometerCapability capability(MeterRegistry registry) {
		return new MicrometerCapability(registry);
	}

}
