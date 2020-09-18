package com.practice.vvr.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.springdemo.entity.Customer;
import com.practice.vvr.springdemo.exception.CustomerNotFoundException;
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
	public Customer getCustomer(@PathVariable int customerId) throws Exception {
		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found " + customerId);
		}

		return customer;
	}

	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// In Dao method we have used saveorupdate method
		// 0 -> null. So, this will force to create new customer instead of update
		// customer.
		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}
	
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		if(tempCustomer == null) {
			throw new CustomerNotFoundException("Customer not found : " + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted the customer : " + customerId;
	}

}
