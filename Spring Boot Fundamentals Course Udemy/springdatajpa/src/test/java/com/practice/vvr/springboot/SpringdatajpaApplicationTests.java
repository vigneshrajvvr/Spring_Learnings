package com.practice.vvr.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.vvr.springboot.entity.Student;
import com.practice.vvr.springboot.repository.StudentRepository;

@SpringBootTest
class SpringdatajpaApplicationTests {
	
	@Autowired
	private StudentRepository studentRepository;

	@Test
	void testSaveStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Ram");
		student.setTestScore(100);
		studentRepository.save(student);
		
		Student saveStudent = studentRepository.findById(1L).get();
		assertNotNull(saveStudent);
		assertEquals(student, saveStudent);
	}

}
