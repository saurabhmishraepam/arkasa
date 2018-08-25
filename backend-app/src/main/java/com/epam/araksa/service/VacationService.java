package com.epam.araksa.service;

import com.epam.araksa.repository.EmployeeVacationRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacationService {

@Autowired
    EmployeeVacationRepository employeeVacationRepository;
    public boolean isOnVacationTodayByUUID(String empUUID) {
        //get the values from csv file

        return false;
    }


    public boolean isOnVacationTodayByEmpId(String empId) {


        return false;
    }

    public boolean isOnVacation(String empUUID, DateTime date) {


        return false;
    }


}