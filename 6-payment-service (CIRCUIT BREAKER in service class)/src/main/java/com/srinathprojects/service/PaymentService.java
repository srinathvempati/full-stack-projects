package com.srinathprojects.service;

import com.srinathprojects.dto.PaymentRequestDto;
import com.srinathprojects.dto.PaymentResponseDto;

public interface PaymentService {
	
	public PaymentResponseDto processPayment(PaymentRequestDto request);
	

}
