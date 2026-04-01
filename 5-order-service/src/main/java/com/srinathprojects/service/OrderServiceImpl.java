package com.srinathprojects.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.controller.OrderController;
import com.srinathprojects.dto.CartItemResponseDto;
import com.srinathprojects.dto.OrderItemResponseDto;
import com.srinathprojects.dto.OrderResponseDto;
import com.srinathprojects.dto.OrderUpdateStatusResponseDto;
import com.srinathprojects.dto.PlaceOrderRequestDto;
import com.srinathprojects.dto.ProductResponseDto;
import com.srinathprojects.dto.UserDto;
import com.srinathprojects.exception.ResourceNotFoundException;
import com.srinathprojects.figenclients.CartFeignClient;
import com.srinathprojects.figenclients.ProductFeignClient;
import com.srinathprojects.figenclients.UserFeignClient;
import com.srinathprojects.model.Order;
import com.srinathprojects.model.OrderItem;
import com.srinathprojects.model.StatusUpdate;
import com.srinathprojects.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private ProductFeignClient productFeignClient;

	@Autowired
	private CartFeignClient cartFeignClient;

	@Override
	public OrderResponseDto placeOrder(PlaceOrderRequestDto request) {
		
		log.info("order service:  placeOrder {} ", request.getUserId());

		UserDto userdto = validateUser(request.getUserId());

		if (userdto == null) {
			throw new ResourceNotFoundException("user not found in DB");
		}

		List<CartItemResponseDto> cartItems = fetchCartItems(request.getUserId());

		if (cartItems == null || cartItems.isEmpty()) {
			throw new ResourceNotFoundException("cart is empty");
		}

		BigDecimal totalPrice = calculateTotalPrice(cartItems);

		List<OrderItem> orderItems = buildOrderItems(cartItems);

		Order order = createOrder(request, totalPrice, orderItems);

		Order dbOrder = orderRepository.save(order);

		cartFeignClient.clearUserCart(request.getUserId());

		return mapToOrderResponse(dbOrder, userdto);
	}

	public OrderResponseDto mapToOrderResponse(Order dbOrder, UserDto userdto) {
		OrderResponseDto response = new OrderResponseDto();
		BeanUtils.copyProperties(dbOrder, response, "items"); // Here Excluding the Items to map manually
		response.setOrderId(dbOrder.getId());
		response.setUserDto(userdto);

		List<OrderItemResponseDto> orderItemsResponse = dbOrder.getItems().stream().map(item -> {
			OrderItemResponseDto itemDto = new OrderItemResponseDto();
			BeanUtils.copyProperties(item, itemDto);
			return itemDto;
		}).collect(Collectors.toList());

		response.setItems(orderItemsResponse);

		return response;
	}

	private Order createOrder(PlaceOrderRequestDto request, BigDecimal totalPrice, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setTotalPrice(totalPrice);
		order.setStatus(StatusUpdate.ORDER_PLACED);
		order.setItems(orderItems);

		for (OrderItem items : orderItems) {

			items.setOrder(order);
		}
		return order;
	}

	private List<OrderItem> buildOrderItems(List<CartItemResponseDto> cartItems) {
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItemResponseDto items : cartItems) {
			ProductResponseDto product = productFeignClient.getProduct(items.getProductId());

			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(items.getProductId());
			log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			log.info("OrderServiceImpl productID {} ", items.getProductId());
			orderItem.setQuantity(items.getQuantity());
			log.info("OrderServiceImpl quantity {} ", items.getQuantity());
			orderItem.setPrice(product.getPrice());
			log.info("OrderServiceImpl price {} ", product.getPrice());
			orderItems.add(orderItem);

		}
		return orderItems;
	}

	private BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {

		BigDecimal total = BigDecimal.ZERO;

		for (CartItemResponseDto item : cartItems) {
			ProductResponseDto product = productFeignClient.getProduct(item.getProductId());
			Integer quantity = item.getQuantity();
			BigDecimal individualPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
			total = total.add(individualPrice);

		}
		return total;

	}

	private List<CartItemResponseDto> fetchCartItems(Long userId) {
		return cartFeignClient.getCartByUserId(userId);
	}

	private UserDto validateUser(Long userId) {

		return userFeignClient.fetchUser(userId.intValue());

	}

	/////////////// Update Order status ////////////////
	@Override
	public OrderUpdateStatusResponseDto updateOrderStatus(Long orderId, String status) {
		log.info("OrderServiceImpl updateOrderStatus {}", orderId);

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("OrderID not found in DB"));

		if (status != null && status.equalsIgnoreCase("SHIPPED")) {
			order.setStatus(StatusUpdate.ORDER_SHIPPED);
			
		} if (status != null && status.equalsIgnoreCase("CANCELLED")) {
			order.setStatus(StatusUpdate.ORDER_CANCELLED);
		}
		 if (status != null && status.equalsIgnoreCase("PLACED")) {
			order.setStatus(StatusUpdate.ORDER_PLACED);
		}
		 if (status != null && status.equalsIgnoreCase("DELIVERED")) {
			order.setStatus(StatusUpdate.ORDER_DELIVERED);
		}
		 if (status != null && status.equalsIgnoreCase("RETURNED")) {
			order.setStatus(StatusUpdate.ORDER_RETURNED);
		}

		Order dbOrderData = orderRepository.save(order);

		return mapToOrderUpdateStatusResponse(dbOrderData);

	}

	
	
	public OrderUpdateStatusResponseDto mapToOrderUpdateStatusResponse(Order dbOrderData) {
		OrderUpdateStatusResponseDto response = new OrderUpdateStatusResponseDto();
	    BeanUtils.copyProperties(dbOrderData, response);
	    
	    return response;
	}
	

	@Override
	public List<OrderResponseDto> getOrdersByUser(Long userId) {
		
		log.info("order service:  getOrdersByUser {} ", userId);

	    UserDto userdto = validateUser(userId);

	    if (userdto == null) {
	        throw new ResourceNotFoundException("user not found in DB");
	    }

	    return orderRepository.findByUserId(userId)
	            .stream()
	            .map(order -> mapToOrderResponse(order, userdto))
	            .collect(Collectors.toList());
	}

	@Override
	public OrderUpdateStatusResponseDto getOderById(Long orderId) {
		
		log.info("order service:  getOderById {} ", orderId);
		
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("OrderID not found in DB"));
		
		return mapToOrderUpdateStatusResponse(order);
	}
}
