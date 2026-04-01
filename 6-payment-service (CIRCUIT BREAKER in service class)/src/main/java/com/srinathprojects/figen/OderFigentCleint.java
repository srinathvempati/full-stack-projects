package com.srinathprojects.figen;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.srinathprojects.dto.OrderUpdateStatusResponseDto;

@FeignClient(name = "order-service", path = "/orders")
public interface OderFigentCleint {
	
	@GetMapping("orderId/{orderId}")
	public OrderUpdateStatusResponseDto fetchOdersByOrderId(@PathVariable Long orderId);
	

}
