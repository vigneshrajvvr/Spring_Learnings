package com.practice.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{
	
	EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
				
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
		
	}

	@Override
	public void save(Employee theEmployee) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save 
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//delete objekt with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id:=employeeId");
		theQuery.setParameter("ëmployeeId", theId);
		
		theQuery.executeUpdate();
	}

}
