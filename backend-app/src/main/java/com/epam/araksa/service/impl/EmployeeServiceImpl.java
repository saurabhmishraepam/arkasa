package com.epam.araksa.service.impl;

import com.epam.araksa.dto.EmployeeResponseDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.araksa.dto.Employee;
import com.epam.araksa.repository.EmployeeRepository;
import com.epam.araksa.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{



	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeResponseDto addUser(Employee employee) {
		DateTime time=new DateTime();
		employee.setDateRegistered(time.getMillis());
		employee.setIsActive(true);

		if (employeeRepository.save(employee) != null) {
			List<EmployeeResponseDto> emp = employeeRepository.findByEmpId(employee.getEmpId());
			EmployeeResponseDto empOne=employeeRepository.findBy_id(emp.get(0).get_id());
			return empOne; 
		}
		return null;
	}

	@Override
	public EmployeeResponseDto getUser(String empId) {
		List<EmployeeResponseDto> emp= employeeRepository.findByEmpId(empId);
		return emp.get(0);
	}


	@Override
	public EmployeeResponseDto getUserById(String uuid) {
		EmployeeResponseDto emp= employeeRepository.findBy_id(uuid);
		return emp;
	}
	@Override
	public void deleteUserByEmpId(String empId){
		employeeRepository.deleteByEmpId(empId);


	}
	@Override
	public void deleteUser(String Id){
		employeeRepository.deleteByEmpId(Id);


	}

}
