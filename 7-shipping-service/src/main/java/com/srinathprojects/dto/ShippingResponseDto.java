package com.srinathprojects.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingResponseDto {


	private Long orderId;

	private String shippingMethod; // STANDARD, NEXTDAY,

	private LocalDateTime shippedAt;

	private LocalDateTime deliveryDate;

	private String status; // shipped, intransit, delivered

	private String carrier;

}
