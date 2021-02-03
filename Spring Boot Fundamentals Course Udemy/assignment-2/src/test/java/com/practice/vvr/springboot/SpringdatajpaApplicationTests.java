package com.practice.vvr.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.vvr.springboot.entity.Student;
import com.practice.vvr.springboot.repository.StudentRepository;

@SpringBootTest
class SpringdatajpaApplicationTests {
	
	private static Logger logger = Logger.getLogger("SpringDataLogger");
	
	@Autowired
	private StudentRepository studentRepository;

	@Test
	void testSaveStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Ram");
		student.setTestScore(100);
		studentRepository.save(student);//Create
		
		student.setName("Ramu");
		studentRepository.save(student);//Update
		
		Student saveStudent = studentRepository.findById(1L).get();//Read
		assertNotNull(saveStudent);
		assertEquals(student, saveStudent);
		
		studentRepository.delete(student);//Delete
	}

}
