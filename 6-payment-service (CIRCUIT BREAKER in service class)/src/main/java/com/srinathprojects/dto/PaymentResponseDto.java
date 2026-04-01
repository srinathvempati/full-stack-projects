package com.srinathprojects.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

	private Long paymentId;
	
	private Long orderId;

	private Long userId;

	private BigDecimal amount;

	private String status;

	private LocalDateTime paymentTime;
	
	private UserDto userDto;

}
