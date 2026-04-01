package com.srinathprojects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.OrderResponseDto;
import com.srinathprojects.dto.OrderUpdateStatusResponseDto;
import com.srinathprojects.dto.PlaceOrderRequestDto;
import com.srinathprojects.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	public OrderResponseDto placeOrder(@RequestBody PlaceOrderRequestDto request) {
		log.info("OrderController placeOrder {}", request.getUserId());
		return orderService.placeOrder(request);
	}
	
	@PutMapping("orderId/{oderId}")
	public OrderUpdateStatusResponseDto updateOrderStatus(@PathVariable Long oderId, @RequestParam String Status) {
		log.info("OrderController updateOrderStatus {}", oderId);
		return orderService.updateOrderStatus(oderId, Status);
		
	}
	
	@GetMapping("/{userId}")
	public List<OrderResponseDto> fetchOdersByUser(@PathVariable Long userId){
		log.info("OrderController fetchOdersByUser {}", userId);
		return orderService.getOrdersByUser(userId);
	}
	
	
	@GetMapping("orderId/{orderId}")
	public OrderUpdateStatusResponseDto fetchOdersByOrderId(@PathVariable Long orderId){
		log.info("OrderController fetchOdersByOrderId {}", orderId);
		return orderService.getOderById(orderId);
	}
}

