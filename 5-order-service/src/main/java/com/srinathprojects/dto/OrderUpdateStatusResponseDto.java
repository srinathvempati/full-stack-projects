package com.srinathprojects.dto;


import java.math.BigDecimal;

import com.srinathprojects.model.StatusUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderUpdateStatusResponseDto {

	private Long id;
	private Long userId;
	private BigDecimal totalPrice;
	private StatusUpdate status;
}
