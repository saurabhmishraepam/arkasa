package com.epam.araksa.service;

import com.epam.araksa.dto.Employee;
import com.epam.araksa.dto.EmployeeResponseDto;

public interface EmployeeService {
	public EmployeeResponseDto addUser(Employee employee);
	public EmployeeResponseDto getUser(String empId);
	public EmployeeResponseDto getUserById(String uuid) ;
	public void deleteUser(String Id);
	public void deleteUserByEmpId(String empId);
}
