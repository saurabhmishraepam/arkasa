package com.epam.araksa.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @PutMapping(value="/updateparking/{empId}")
    public void updateParking(@PathVariable String empId){




    }



}
