package com.srinathprojects.dto;

import java.math.BigDecimal;
import java.util.List;

import com.srinathprojects.model.StatusUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDto {
	
	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private StatusUpdate status;
	private List<OrderItemResponseDto> items;
	private UserDto userDto;

}
