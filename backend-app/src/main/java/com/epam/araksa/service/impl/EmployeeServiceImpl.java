package com.epam.araksa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.araksa.dto.Employee;
import com.epam.araksa.repository.EmployeeRepository;
import com.epam.araksa.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository; 
	
	@Override
	public String addUser(Employee employee) {
		Employee e =  employeeRepository.save(employee);
		return e.getEmpId();
		 
	}
}
