package com.practice.vvr.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.springboot.entity.Student;
import com.practice.vvr.springboot.repository.StudentRepository;

@RestController
public class StudentController {
	
	private StudentRepository studentRepository;
	
	public StudentController(StudentRepository productRepository) {
		this.studentRepository = productRepository;
	}
	
	@GetMapping("/students")
	public List<Student> getProducts() {
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/{id}")
	public Student getProduct(@PathVariable("id") int id) {
		return studentRepository.findById(id).get();
	}
	
	@PostMapping("/students")
	public Student createProduct(@RequestBody Student product) {
		return studentRepository.save(product);
	}
	
	@PutMapping("/students")
	public Student updateProduct(@RequestBody Student product) {
		return studentRepository.save(product);
	}
	
	@DeleteMapping("/students/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		 studentRepository.deleteById(id);
	}

}
