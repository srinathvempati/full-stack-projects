package com.srinathprojects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.CartItemRequestDto;
import com.srinathprojects.dto.CartItemResponseDto;
import com.srinathprojects.service.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartItemResponseDto addItemToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
		
		log.info("cart controller : addItemToCart {}", cartItemRequestDto.getProductId());
		
		return cartService.addToCart(cartItemRequestDto);

	}
	
	@PutMapping
	public CartItemResponseDto updateQuantity(@RequestBody CartItemRequestDto cartItemRequestDto) {
		
		log.info("cart controller : updateQuantity {}", cartItemRequestDto.getProductId());
		
		return cartService.updateQuanity(cartItemRequestDto);

	}

	@GetMapping("/{userId}")
	public List<CartItemResponseDto> getCartByUserId(@PathVariable Long userId) {
		
		log.info("cart controller : getCartByUserId {}", userId);
		
		return cartService.getUserCart(userId);

	}

	@DeleteMapping("/clear/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void clearUserCart(@PathVariable Long userId) {
		
		log.info("cart controller : clearUserCart {}", userId);
		
		cartService.clearCart(userId);
	}
	
	//combination of path varaiable and request param
	@DeleteMapping("/remove/{productId}")
	public void removeItemFromCart(@PathVariable Long productId, @RequestParam Long userId) {
		
		log.info("cart controller : removeItemFromCart {}", userId);
		
		cartService.removeItem(userId, productId);
		
	}
}
