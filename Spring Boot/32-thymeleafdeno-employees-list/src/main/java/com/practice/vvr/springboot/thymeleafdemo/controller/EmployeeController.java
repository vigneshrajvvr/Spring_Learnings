package com.practice.vvr.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.vvr.springboot.thymeleafdemo.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	// To store employee data
	private List<Employee> theEmployees;
	
	@PostConstruct
	private void loadData() {
		
		// create employees
		Employee employee1 = new Employee(1, "Leslie", "Andrews", "leslie@gmail.com");
		Employee employee2 = new Employee(2, "Emma", "Baumgarten", "emma@yahoo.com");
		Employee employee3 = new Employee(3, "Avani", "Gupta", "avani@gmail.com");
		
		// create the list
		theEmployees = new ArrayList<>();
		
		// add employees in the list
		theEmployees.add(employee1);
		theEmployees.add(employee2);
		theEmployees.add(employee3);
		
	}
	
	// add mapping for /list
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// model attribute to hold employee list
		theModel.addAttribute("employees", theEmployees);
		
		return "list-employees";
		
	}
	

}
