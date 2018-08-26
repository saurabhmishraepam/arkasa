package com.epam.araksa.controllers;

import com.epam.araksa.datacache.ParkingBookedCache;
import com.epam.araksa.dto.Dashboard;
import com.epam.araksa.dto.EmployeeLocation;
import com.epam.araksa.service.RelativeLocationCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentParkingStatusController {

    @Autowired
    RelativeLocationCache relativeLocationCache;

    @PutMapping (value = "/emp/getLiveStatus")
    public Dashboard getLiveStatus(@RequestBody EmployeeLocation employeeLocation) {
        Dashboard dashboard= relativeLocationCache.getLocationCache(employeeLocation);
        dashboard.setFloatersAvailable(
        dashboard.getFloaterSlots()- ParkingBookedCache.booked.size());
        dashboard.setOccupiedSlots(ParkingBookedCache.booked.size());
        System.out.println("after adding new location details:: "+employeeLocation);

       return dashboard;
    }


}
