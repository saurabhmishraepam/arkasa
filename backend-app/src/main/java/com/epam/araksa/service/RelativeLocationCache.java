package com.epam.araksa.service;


import com.epam.araksa.core.EmpLocationPool;
import com.epam.araksa.dto.Dashboard;
import com.epam.araksa.dto.EmployeeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelativeLocationCache {

    @Autowired
    private EmpLocationPool empLocationPool;


    public void addToLocationCache(EmployeeLocation employeeLocation){

        empLocationPool.updateEmployeeLocation(employeeLocation);
    }

    public Dashboard getLocationCache(EmployeeLocation employeeLocation){
        System.out.println(empLocationPool);
       return empLocationPool.getEmployeeFromCache(employeeLocation);
    }



}
