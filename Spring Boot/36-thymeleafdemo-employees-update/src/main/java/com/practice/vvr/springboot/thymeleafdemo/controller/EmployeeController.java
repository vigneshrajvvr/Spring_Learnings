package com.practice.vvr.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		return "employees/list-employees";
		
	}
	
	// add mapping for /showFormForAdd
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
				
		return "employees/employee-form";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		// set model attribute to prepopulate the data
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	// add mapping for /save
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	// add mapping for /delete
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		
		// delete employee by employee id
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	

}
