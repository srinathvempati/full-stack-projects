package com.srinathprojects.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.PaymentRequestDto;
import com.srinathprojects.dto.UserDto;
import com.srinathprojects.exception.UserNotFoundException;
import com.srinathprojects.figen.UserFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserIntegrationService {

	@Autowired
	private UserFeignClient userFeignClient;

	// Circuit breaker implementation here its checking wheather user service is
	// aviable or not

	@CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallBack")
	public UserDto fetchUserId(PaymentRequestDto request) {
		UserDto userDto = userFeignClient.fetchUser(request.getUserId().intValue());

		if (userDto == null) {
			throw new UserNotFoundException("User not found in DB");
		}
		return userDto;
	}

	public UserDto userFallBack(PaymentRequestDto request, Throwable t) {

		log.error("USer Service FallBack {}", t.getMessage());
		
		throw new RuntimeException("user service is unavaiable. Please try again later.");


	}

}
