package com.srinathprojects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.PaymentRequestDto;
import com.srinathprojects.dto.PaymentResponseDto;
import com.srinathprojects.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public PaymentResponseDto makePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
		log.info("payment controller : makePayment {}", paymentRequestDto.getUserId() );
		return paymentService.processPayment(paymentRequestDto);
		
	}

}
