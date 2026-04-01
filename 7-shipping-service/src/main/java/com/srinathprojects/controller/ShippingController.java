package com.srinathprojects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.ShippingRequestDto;
import com.srinathprojects.dto.ShippingResponseDto;
import com.srinathprojects.service.ShippingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shipping")
@Slf4j
public class ShippingController {
	
	@Autowired
	private ShippingService shippingService;
	
	
	@PostMapping
	public ShippingResponseDto shippingRequest (@RequestBody ShippingRequestDto reShippingRequestDto) {
		log.info("shipping controller : shippingRequest {}", reShippingRequestDto.getCarrier());
		return shippingService.shippingOrder(reShippingRequestDto);
		
	}
	
	@PutMapping("/orderId/{orderId}")
	public void updateShippingStatus(@PathVariable Long orderId, @RequestParam String Status) {
		log.info("shipping controller : updateShippingStatus {}", orderId);
		shippingService.updateShippingStatus(orderId, Status);
	}

}
