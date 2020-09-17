package com.practice.vvr.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.springdemo.entity.Customer;
import com.practice.vvr.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customer
	@GetMapping("/customer")
	public List<Customer> getCustomers() {
		List<Customer> customers = customerService.getCustomers();

		return customers;
	}

	// add mapping for GET /customer/{customerId}
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new RuntimeException("Customer not found");
		}

		return customer;
	}

}
