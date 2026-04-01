package com.srinathprojects.service;

import java.util.List;

import com.srinathprojects.dto.OrderResponseDto;
import com.srinathprojects.dto.OrderUpdateStatusResponseDto;
import com.srinathprojects.dto.PlaceOrderRequestDto;

public interface OrderService {
	
	OrderResponseDto placeOrder(PlaceOrderRequestDto request);
	
	OrderUpdateStatusResponseDto updateOrderStatus(Long orderId, String status);
	
	List<OrderResponseDto> getOrdersByUser(Long userId);
	
	OrderUpdateStatusResponseDto getOderById(Long orderId);
	
	

}
