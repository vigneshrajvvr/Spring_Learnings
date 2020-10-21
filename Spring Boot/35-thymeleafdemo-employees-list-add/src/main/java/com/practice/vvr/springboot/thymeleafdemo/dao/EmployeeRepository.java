package com.practice.vvr.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.vvr.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// order the list by last name
	
	public List<Employee> findAllByOrderByLastName();
	
}
