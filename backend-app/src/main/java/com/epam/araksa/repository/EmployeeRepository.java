package com.epam.araksa.repository;

import com.epam.araksa.dto.EmployeeResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.araksa.dto.Employee;

import java.util.List;

@Repository
	public interface EmployeeRepository extends MongoRepository<Employee, Long>{
	@Query("{empId:'?0'}")
	List<EmployeeResponseDto> findByEmpId(String empId);


	@Query("{_id:'?0'}")
	EmployeeResponseDto findBy_id(String _id);

	List <EmployeeResponseDto> deleteByEmpId(String empId);
//	ist <EmployeeResponseDto> deleteBy_id(String Id);

	}
