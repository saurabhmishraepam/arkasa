package com.epam.araksa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.araksa.dto.Employee;

@Repository
	public interface EmployeeRepository extends MongoRepository<Employee, Long>{

		@Query("{empId:'?0'}")
		Employee findByEmpId(Long empId);
	}
