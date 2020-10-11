package com.practice.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
