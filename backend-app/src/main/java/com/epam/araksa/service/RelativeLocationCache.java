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
        System.out.println(empLocationPool);
        DateTime dt=new DateTime();
        String month=dt.getMonthOfYear()<10?"0"+dt.getMonthOfYear():dt.getMonthOfYear()+"";
        String dateToday=dt.getYear()+"-"+month+"-"+dt.getDayOfMonth();
        BasicDBObject query = new BasicDBObject();

        //System.out.println("---"+predictedTimesEmp.findByforDate(dateToday));
        //predictedTimesEmp.findByforDate(dateToday);
       return empLocationPool.getEmployeeFromCache(employeeLocation);
    }



}
