package com.srinathprojects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.srinathprojects.service.PaymentService;

public class MainClass {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		PaymentService paymentService = context.getBean(PaymentService.class);
		paymentService.makePayment(500);
		
		
		

	}

}
