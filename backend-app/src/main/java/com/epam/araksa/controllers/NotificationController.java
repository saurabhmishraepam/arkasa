package com.epam.araksa.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.araksa.dto.EmployeeStatus;

@RestController
public class NotificationController {

	@PutMapping("notification")
	public String updateEmployeeStatus(@RequestBody EmployeeStatus employeeStatus) {
		System.out.println("Employee status :"+employeeStatus);
		return "Updated";
	}
}
