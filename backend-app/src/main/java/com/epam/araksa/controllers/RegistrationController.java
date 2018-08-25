package com.epam.araksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.araksa.dto.Employee;
import com.epam.araksa.service.EmployeeService;

@RestController
public class RegistrationController {


	@Autowired
	EmployeeService employeeService;
	
	@PutMapping( value="/register/add")
	public String register(@RequestBody Employee employee) {
		return employeeService.addUser(employee);
	}
}
