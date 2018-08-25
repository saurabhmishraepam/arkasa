package com.epam.araksa.service;

import com.epam.araksa.DummyEmployeePrep.HolidaysCalenderPrep;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;

public class HolidayCheckService {

    public boolean isHoliday(){

        int dayNum=new DateTime().getDayOfYear();

        return HolidaysCalenderPrep.dayOfYearHoliday.contains(dayNum);




    }


}
