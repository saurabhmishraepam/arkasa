package com.epam.araksa.datacache;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
@Component
public class ParkingBookedCache {

    public static Map<Long, String> booked=new HashMap<>();

}
