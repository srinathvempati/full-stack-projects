package com.srinathprojects;

import org.springframework.stereotype.Component;

@Component
public class Car {

	public Car() {
		System.out.println("car Default constructor");
	}
	
	public void drive() {
		System.out.println("car is running");
	}
}
