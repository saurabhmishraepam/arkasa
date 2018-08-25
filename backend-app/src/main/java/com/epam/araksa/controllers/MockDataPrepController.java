package com.epam.araksa.controllers;

import com.epam.araksa.DummyEmployeePrep.DummyEmployeePrep;
import com.epam.araksa.DummyEmployeePrep.EmployeeDailyTimingPrep;
import com.epam.araksa.DummyEmployeePrep.HolidaysCalenderPrep;
import com.epam.araksa.DummyEmployeePrep.VacationDataPrep;
import com.epam.araksa.repository.EmployeeLoginTimeRepository;
import com.epam.araksa.repository.EmployeeRepository;
import com.epam.araksa.repository.EmployeeVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MockDataPrepController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeLoginTimeRepository employeeLoginTimeRepository;

    @Autowired
    private EmployeeVacationRepository employeeVacationRepository;

    @GetMapping(value = "/mockdata/users/{count}")
    public void prepMockData(@PathVariable int count){

        DummyEmployeePrep.prepDummyEmployes(count).forEach(
                emp->
                        employeeRepository.save(emp)
        );


    }
    @GetMapping(value="/mockdata/userlogin/{numdays}")
    public void prepLoginMockData(@PathVariable int numdays){

        EmployeeDailyTimingPrep.mockLoginTimingsPrep( DummyEmployeePrep.empIds,numdays).forEach(
                log-> employeeLoginTimeRepository.save(log)
        );

    }
    @GetMapping(value="/mockdata/vacation/{noOfDays}")
    public void prepVacationMockData(@PathVariable int noOfDays){

        VacationDataPrep.createMockVacation(DummyEmployeePrep.empIds,noOfDays);
        VacationDataPrep.dateWise.forEach(

                (day, val)->{
                    HashMap<Integer, String> mapVal=new HashMap<>();
                    mapVal.put(day,val);
                    employeeVacationRepository.save(mapVal);}

        );
    }

}
