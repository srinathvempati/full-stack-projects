package com.srinathprojects.feignClinet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srinathprojects.dto.OrderUpdateStatusResponseDto;

@FeignClient(name = "order-service", path = "/orders")
public interface OrderFeignClient {
	
	@GetMapping("orderId/{orderId}")
	public OrderUpdateStatusResponseDto fetchOdersByOrderId(@PathVariable Long orderId);
	
	@PutMapping("orderId/{oderId}")
	public OrderUpdateStatusResponseDto updateOrderStatus(@PathVariable Long oderId, @RequestParam String Status) ;

}
