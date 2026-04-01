package com.srinathprojects.service;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentProcess{
	
	public CreditCardPayment() {
		System.out.println("creditcardpayment default constructor");
	}

	@Override
	public void pay(double amount) {
		System.out.println("paid " +  amount + " using credit card");
		
	}

}
