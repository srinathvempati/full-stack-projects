package com.srinathprojects.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorApi> handleException(UserNotFoundException ex) {
		log.error("userNot found Exception {}", ex.getMessage());
		
		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMessage(ex.getMessage());
		errorApi.setErrorType("Validation error");
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		
		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApi> handleException(Exception ex) {
		log.error("Internal Server Error {}", ex.getMessage());
		
		ErrorApi errorApi = new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setErrorType(ex.getMessage());
		errorApi.setMessage("something went wrong, please check the logs");
		errorApi.setErrorType(null);
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
