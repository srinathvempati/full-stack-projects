package com.srinathprojects.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

	private Long orderId;

	private Long userId;

	private BigDecimal amount;

}
