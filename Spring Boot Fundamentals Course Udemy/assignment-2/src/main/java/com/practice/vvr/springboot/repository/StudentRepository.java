package com.practice.vvr.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.vvr.springboot.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
