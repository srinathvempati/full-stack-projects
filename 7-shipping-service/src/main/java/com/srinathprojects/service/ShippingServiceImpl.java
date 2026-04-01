package com.srinathprojects.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.OrderUpdateStatusResponseDto;
import com.srinathprojects.dto.ShippingRequestDto;
import com.srinathprojects.dto.ShippingResponseDto;
import com.srinathprojects.feignClinet.OrderFeignClient;
import com.srinathprojects.models.ShippingInfo;
import com.srinathprojects.repository.ShippingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	ShippingRepository shippingRepository;

	@Autowired
	private OrderFeignClient orderFeignClient;

	@Override
	public ShippingResponseDto shippingOrder(ShippingRequestDto shippingRequestDto) {
		
		log.info("shipping service: shippingOrder {}", shippingRequestDto.getCarrier());

		OrderUpdateStatusResponseDto orderDto = orderFeignClient.fetchOdersByOrderId(shippingRequestDto.getOrderId());

		if (orderDto == null) {
			throw new RuntimeException("OrderID not found in DB");
		}
		
		ShippingInfo shippingInfo = new ShippingInfo();
		
		BeanUtils.copyProperties(shippingRequestDto, shippingInfo);
		shippingInfo.setStatus("SHIPPED");
		shippingInfo.setShippedAt(LocalDateTime.now());
		
		ShippingInfo dbShipping = shippingRepository.save(shippingInfo);
		
		orderFeignClient.updateOrderStatus(shippingRequestDto.getOrderId(), "SHIPPED");
		

		return mapToDto(dbShipping);
	}

	private ShippingResponseDto mapToDto(ShippingInfo dbShipping) {
		
		ShippingResponseDto shippingResponseDto = new ShippingResponseDto();
		
		BeanUtils.copyProperties(dbShipping, shippingResponseDto);
		shippingResponseDto.setShippedAt(shippingResponseDto.getShippedAt());
		return shippingResponseDto;
	}

	@Override
	public void updateShippingStatus(Long orderId, String status) {
		
		log.info("shipping service: updateShippingStatus {}", orderId);
		
		ShippingInfo shippingInfo = shippingRepository.findByOrderId(orderId);
		
		if(shippingInfo == null) {
			throw new RuntimeException("OrderID not found in DB");
		}
		
		shippingInfo.setStatus(status);
		
		shippingInfo.setDeliveryDate("DELIVERED".equals(status) ? LocalDateTime.now() : null);
		
		shippingRepository.save(shippingInfo);
		
		
		
		if("DELIVERED".equals(status)) {
			
			orderFeignClient.updateOrderStatus(orderId, status);
			
		}
		
	}

}
