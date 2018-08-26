package com.epam.araksa.controllers;

import com.epam.araksa.datacache.ParkingBookedCache;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @PutMapping(value="/updateparking/{empId}")
    public void updateParking(@PathVariable String empId){

        if(!ParkingBookedCache.booked.containsValue(empId)){
            ParkingBookedCache.booked.put(new DateTime().getMillis(),empId);
        }

    }

    @GetMapping(value="/updateparking/")
    public Map<Long, String> updateParking(){
           return ParkingBookedCache.booked;
    }



}
