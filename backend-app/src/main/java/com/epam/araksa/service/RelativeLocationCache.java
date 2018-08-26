package com.epam.araksa.service;


import com.epam.araksa.core.EmpLocationPool;
import com.epam.araksa.dto.Dashboard;
import com.epam.araksa.dto.EmployeeLocation;
import com.epam.araksa.dto.PredictedTime;
import com.epam.araksa.repository.PredictedTimesEmp;
import com.mongodb.BasicDBObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelativeLocationCache {

    @Autowired
    private EmpLocationPool empLocationPool;

    @Autowired
    PredictedTimesEmp predictedTimesEmp;
    public void addToLocationCache(EmployeeLocation employeeLocation){

        empLocationPool.updateEmployeeLocation(employeeLocation);
    }

    public Dashboard getLocationCache(EmployeeLocation employeeLocation){

       return empLocationPool.getEmployeeFromCache(employeeLocation);
    }



}
