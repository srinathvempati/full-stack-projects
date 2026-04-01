package com.srinathprojects.service;

import com.srinathprojects.AppConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {


	private PaymentProcess paymentProcess;
	
	@Autowired
	private  PaymentService(PaymentProcess paymentProcess)
	{
		this.paymentProcess = paymentProcess;
		
	}
	
	public void makePayment(double amount) {
		paymentProcess.pay(amount);
		
	}
}
