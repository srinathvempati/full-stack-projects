package com.srinathprojects.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Setter
@Getter
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private LocalDateTime addedAt = LocalDateTime.now();

}
