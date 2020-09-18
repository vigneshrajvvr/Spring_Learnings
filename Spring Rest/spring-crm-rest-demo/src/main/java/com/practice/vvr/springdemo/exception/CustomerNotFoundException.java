package com.practice.vvr.springdemo.exception;

public class CustomerNotFoundException extends Exception {

	public CustomerNotFoundException(String message) {
		super("Customer not found");
	}

}
