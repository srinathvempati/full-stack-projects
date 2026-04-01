package com.srinathprojects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.CartItemRequestDto;
import com.srinathprojects.exception.ResourceNotFoundException;
import com.srinathprojects.feignClients.ProductFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductIntegrationService {

	@Autowired
	private ProductFeignClient productFeignClient;

	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "productFallBack")
	public boolean productExist(CartItemRequestDto cartItemResponseDto) {

		boolean productExists = productFeignClient.isProductExist(cartItemResponseDto.getProductId());

		if (!productExists) {
			throw new ResourceNotFoundException("Product Does not exist in DB");
		}

		return productExists;
	}
	
	
	public boolean productFallBack(CartItemRequestDto cartItemResponseDto, Throwable t) {

		log.error("Product Service FallBack {}", t.getMessage());
		
		throw new RuntimeException("Product service is unavaiable. Please try again later.");


	}

}
