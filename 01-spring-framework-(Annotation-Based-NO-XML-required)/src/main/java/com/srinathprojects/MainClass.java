package com.srinathprojects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		// here we no need to call the Car or Engine class bcz we are using @component
		
		Car car = context.getBean(Car.class);
		car.drive();
		
		

	}

}
