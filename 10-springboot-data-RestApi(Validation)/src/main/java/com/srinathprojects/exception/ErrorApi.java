package com.srinathprojects.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ErrorApi {
	
	private String message;
	private String status;
	private LocalDateTime localDateTime;
	private String errorType;

}
