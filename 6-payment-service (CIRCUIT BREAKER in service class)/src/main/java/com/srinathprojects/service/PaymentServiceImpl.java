package com.srinathprojects.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.OrderUpdateStatusResponseDto;
import com.srinathprojects.dto.PaymentRequestDto;
import com.srinathprojects.dto.PaymentResponseDto;
import com.srinathprojects.dto.UserDto;
import com.srinathprojects.figen.OderFigentCleint;
import com.srinathprojects.figen.UserFeignClient;
import com.srinathprojects.models.Payment;
import com.srinathprojects.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OderFigentCleint oderFigentCleint;

	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private UserIntegrationService userIntegrationService;

	@Override
	public PaymentResponseDto processPayment(PaymentRequestDto request) {

		log.info("payment service : processPayment {}", request.getUserId());

		OrderUpdateStatusResponseDto orderDto = oderFigentCleint.fetchOdersByOrderId(request.getOrderId());

		if (orderDto == null) {
			throw new RuntimeException("OrderID not found in DB");
		}

		UserDto userDto = userIntegrationService.fetchUserId(request);   // here its trigger CircuitBreaker

		Payment payment = new Payment();

		BeanUtils.copyProperties(request, payment);
		payment.setStatus("SUCCESS");

		paymentRepository.save(payment);

		return mapToDto(payment, userDto);
	}

	

	private PaymentResponseDto mapToDto(Payment payment, UserDto userDto) {

		PaymentResponseDto response = new PaymentResponseDto();

		BeanUtils.copyProperties(payment, response);
		response.setPaymentId(payment.getId());
		response.setUserDto(userDto);
		return response;
	}

}
