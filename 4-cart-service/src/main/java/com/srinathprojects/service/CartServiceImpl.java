package com.srinathprojects.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.CartItemRequestDto;
import com.srinathprojects.dto.CartItemResponseDto;
import com.srinathprojects.dto.UserDto;
import com.srinathprojects.entity.CartItem;
import com.srinathprojects.exception.ResourceNotFoundException;
import com.srinathprojects.feignClients.ProductFeignClient;
import com.srinathprojects.feignClients.UserFeignClient;
import com.srinathprojects.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductIntegrationService productIntegrationService;

	@Override
	public CartItemResponseDto addToCart(CartItemRequestDto cartItemResponseDto) {
		
		log.info("cart service : addToCart {}", cartItemResponseDto.getProductId());

		// Before adding details in to cart first we need to check USER and PRODUCT
		// EXISTS or NOT
		
		productIntegrationService.productExist(cartItemResponseDto);    // here we are applying CIRCUIT BREAKER
		
		
		
		
		UserDto userDto = userFeignClient.fetchUser(cartItemResponseDto.getUserId().intValue());
		
		if(userDto == null) {
			throw new ResourceNotFoundException("User Does not exist in DB");
		}

		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(cartItemResponseDto, cartItem);

		CartItem dbCartItem = cartItemRepository.save(cartItem);
		return mapToResponseDto(dbCartItem);
	}

	public CartItemResponseDto mapToResponseDto(CartItem dbCartItem) {

		CartItemResponseDto response = new CartItemResponseDto();
		BeanUtils.copyProperties(dbCartItem, response);

		return response;
	}

	@Override
	public List<CartItemResponseDto> getUserCart(Long userId) {
		
		log.info("cart service : getUserCart {}", userId);

		return cartItemRepository.findByUserId(userId).stream().map(this::mapToResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void removeItem(Long userId, Long productId) {
		
		log.info("cart service : removeItem {}", userId);

		cartItemRepository.deleteByUserIdAndProductId(userId, productId);

	}

	@Override
	public CartItemResponseDto updateQuanity(CartItemRequestDto request) {
		
		log.info("cart service : updateQuanity {}", request.getProductId());

		CartItem cartItem = cartItemRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId())
				.orElseThrow(() -> new RuntimeException("Item not in the cart"));

		cartItem.setQuantity(request.getQuantity());

		CartItem dbCart = cartItemRepository.save(cartItem);

		return mapToResponseDto(dbCart);
	}

	@Override
	@Transactional
	public void clearCart(Long userId) {
		log.info("cart service : clearCart {}", userId);
		cartItemRepository.deleteByUserId(userId);

	}

}
