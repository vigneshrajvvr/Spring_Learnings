package com.practice.vvr.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.vvr.springboot.thymeleafdemo.entity.Employee;
import com.practice.vvr.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// add mapping for /list
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		// model attribute to hold employee list
		theModel.addAttribute("employees", theEmployees);
		
		return "list-employees";
		
	}
	

}
