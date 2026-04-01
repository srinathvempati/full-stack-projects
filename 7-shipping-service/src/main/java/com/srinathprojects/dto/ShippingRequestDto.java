package com.srinathprojects.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequestDto {

	private Long orderId;

	private String shippingMethod; 

	private String carrier;
}
