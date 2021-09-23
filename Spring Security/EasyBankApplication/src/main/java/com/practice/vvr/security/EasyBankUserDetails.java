package com.practice.vvr.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.vvr.security.entity.Customer;
import com.practice.vvr.security.repository.CustomerRepository;

@Service
public class EasyBankUserDetails implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Customer> customer = customerRepository.findByEmail(username);
		if(customer.size() == 0) {
			throw new UsernameNotFoundException("User Details not found for the user : " + username);
		}
		
		return new SecurityCustomer(customer.get(0));
	}

}
