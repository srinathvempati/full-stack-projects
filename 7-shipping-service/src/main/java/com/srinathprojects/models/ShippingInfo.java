package com.srinathprojects.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "shipping_info")
public class ShippingInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long orderId;
	
	private String shippingMethod; //STANDARD, NEXTDAY, 
	
	private LocalDateTime shippedAt;
	
	private LocalDateTime deliveryDate;
	
	private String status; // shipped, intransit, delivered
	
	private String carrier; //fedex, ups
	
	//  order(we get userinfo)
	
}
