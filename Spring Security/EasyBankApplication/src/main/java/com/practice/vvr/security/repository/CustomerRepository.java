package com.practice.vvr.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.vvr.security.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public List<Customer> findByEmail(String email);
	
}
