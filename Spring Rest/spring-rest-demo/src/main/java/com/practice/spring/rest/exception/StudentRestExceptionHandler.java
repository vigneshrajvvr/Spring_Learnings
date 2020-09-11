package com.practice.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.practice.spring.rest.entity.StudentErrorResponse;

@ControllerAdvice
public class StudentRestExceptionHandler {
	// Exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// Exception handler to catch any exception
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handlerException(Exception exc) {

		StudentErrorResponse error = new StudentErrorResponse();

		error.setStatus(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}
}
