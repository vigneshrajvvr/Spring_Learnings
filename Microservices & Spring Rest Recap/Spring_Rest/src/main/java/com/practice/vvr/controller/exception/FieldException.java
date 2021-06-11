package com.practice.vvr.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LENGTH_REQUIRED)
public class FieldException extends RuntimeException {
	
	public FieldException(String message) {
		super(message);
	}
	
}
