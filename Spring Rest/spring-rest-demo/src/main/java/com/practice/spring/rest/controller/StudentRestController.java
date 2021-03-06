package com.practice.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring.rest.entity.Student;
import com.practice.spring.rest.entity.StudentErrorResponse;
import com.practice.spring.rest.exception.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;

	// @PostConstruct to load the student data ... only once!

	@PostConstruct
	public void loadData() {

		theStudents = new ArrayList<>();

		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rosie"));
		theStudents.add(new Student("Mary", "Smith"));

	}

	// End point to retreive all the students

	@GetMapping("/students")
	public List<Student> getStudents() {

		return theStudents;

	}

	// End point to retrieve student at a index

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		// Exception handling code if studentId is invalid
		if ((studentId < 0) || (studentId >= theStudents.size())) {
			throw new StudentNotFoundException("Student id not found : " + studentId);
		}

		return theStudents.get(studentId);
	}

}
