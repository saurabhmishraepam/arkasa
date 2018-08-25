package com.epam.araksa.controllers;


import com.epam.araksa.dto.EmployeeLocation;
import com.epam.araksa.dto.LocationUpdaterServiceResponse;
import com.epam.araksa.service.EmployeeService;
import com.epam.araksa.service.LocationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationUpdateController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LocationUpdateService locationUpdateService;

    @PostMapping(value = "/locationUpdate")
    public LocationUpdaterServiceResponse updateLocation(@RequestBody EmployeeLocation empLocation) {
        // add field to update user confirmation

        if(empLocation.isComing()) {
            if (employeeService.getUserById(empLocation.getId()) != null) {
                return locationUpdateService.locationUpdater(empLocation);
            }
        }else{
        }
        return null;
    }




}

