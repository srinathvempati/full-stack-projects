package com.srinathprojects.service;

import com.srinathprojects.dto.ShippingRequestDto;
import com.srinathprojects.dto.ShippingResponseDto;

public interface ShippingService {
	
	
	ShippingResponseDto shippingOrder(ShippingRequestDto shippingRequestDto);
	
	
	void updateShippingStatus(Long orderId, String status);

}
