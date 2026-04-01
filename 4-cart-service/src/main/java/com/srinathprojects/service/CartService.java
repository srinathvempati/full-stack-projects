package com.srinathprojects.service;

import java.util.List;

import com.srinathprojects.dto.CartItemRequestDto;
import com.srinathprojects.dto.CartItemResponseDto;

public interface CartService {
	
	public CartItemResponseDto addToCart(CartItemRequestDto cartItemResponseDto);
	
	public List<CartItemResponseDto> getUserCart(Long userId);
	
	public void removeItem(Long userId, Long productId);
	
	public CartItemResponseDto updateQuanity(CartItemRequestDto request);
	
	void clearCart(Long userId);

}
