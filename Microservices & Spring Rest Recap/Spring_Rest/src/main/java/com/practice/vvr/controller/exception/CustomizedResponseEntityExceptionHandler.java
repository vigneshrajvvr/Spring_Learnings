package com.practice.vvr.controller.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FieldException.class)
	public final ResponseEntity<Object> handleFieldException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(), "Validation Error", ex.getBindingResult().toString());
		
		return handleExceptionInternal(ex, null, headers, status, request);
	}
}
