package com.epam.araksa.DummyEmployeePrep;

import com.epam.araksa.dto.ClassifiedWorkingDays;
import com.epam.araksa.dto.LogTime;
import com.epam.araksa.dto.Season;
import com.epam.araksa.service.HolidayCheckService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.*;

public class EmployeeDailyTimingPrep {



    public static Map<String, Float> distanceInKms = new HashMap<>();


    public static List<LogTime> mockLoginTimingsPrep(List<String> emps, int numberOfMonths) {

        DateTime startDay = new DateTime().minusDays(numberOfMonths);
        DateTime current = new DateTime();
        Random rnd = new Random(123);
        List<LogTime> logs = new ArrayList<>();
        for (; !(startDay.dayOfMonth().get() == current.dayOfMonth().get() &&
                startDay.monthOfYear().get() == current.monthOfYear().get());
             startDay = startDay.plusDays(1)) {
            System.out.println("Inside");
            HolidayCheckService checkService=new HolidayCheckService();
         /*   if(checkService.isHoliday()){
                System.out.println("Inside holiday");
                continue;
            }*/
            if (startDay.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                    startDay.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                System.out.println("Inside holiday1");
                continue;
            }
            System.out.println("Inside--1");
            for (String st : emps) {
                float distance = distanceInKms.containsKey(st) ? distanceInKms.get(st) : rnd.nextInt(40);
                int randTimeVarianceInMinutes = rnd.nextInt(20);
                System.out.println("----"+distance+"  randTimeVarianceInMinutes "+randTimeVarianceInMinutes);
                randTimeVarianceInMinutes=(int)(distance*((float)(randTimeVarianceInMinutes/2.0f)));
                randTimeVarianceInMinutes*=10;
                int inTimeMin = rnd.nextInt(60);

                int inTimeHr = rnd.nextInt(13);
                inTimeHr = (inTimeHr > 4 && inTimeHr <= 8 && inTimeHr == 0) ? inTimeHr + 4 : inTimeHr;

                inTimeHr = inTimeHr < 7 ? inTimeHr + 12 : inTimeHr;

                DateTime startTime = new DateTime(startDay.year().get(), startDay.monthOfYear().get(), startDay.dayOfMonth().get(), inTimeHr, inTimeMin);
                DateTime inTime = startTime.plusMinutes(randTimeVarianceInMinutes);
                LogTime tm = new LogTime();
                tm.setEmpId(st);
                tm.setStartTime(startTime.getMillis());
                tm.setInTime(inTime.getMillis());
                tm.setMinutesTaken(randTimeVarianceInMinutes);
                tm.setDay(startDay.getMillis());
                if (startDay.getDayOfWeek() == DateTimeConstants.MONDAY)
                    tm.setcWD(ClassifiedWorkingDays.AW);
                if (startDay.getDayOfWeek() == DateTimeConstants.FRIDAY)
                    tm.setcWD(ClassifiedWorkingDays.BW);
                else{

                    System.out.println("-----------"+startDay.getDayOfWeek()+" "+DateTimeConstants.SATURDAY);
                }
                if(startDay.getMonthOfYear()==DateTimeConstants.DECEMBER   ||
                        startDay.getMonthOfYear()==DateTimeConstants.NOVEMBER
                        ||
                        startDay.getMonthOfYear()==DateTimeConstants.OCTOBER
                        ||
                        startDay.getMonthOfYear()==DateTimeConstants.JANUARY
                        ){

                    tm.setSeason(Season.WINTER);
                }else if(startDay.getMonthOfYear()==DateTimeConstants.FEBRUARY ||

                        startDay.getMonthOfYear()==DateTimeConstants.MARCH
                        ||startDay.getMonthOfYear()==DateTimeConstants.APRIL
                        || startDay.getMonthOfYear()==DateTimeConstants.MAY ||
                        startDay.getMonthOfYear()==DateTimeConstants.JUNE){
                    tm.setSeason(Season.SUMMER);

                }else{
                    tm.setSeason(Season.RAINY);
                }
                System.out.println("Inside--1333"+tm);
                logs.add(tm);
            }
        }
        return logs;
    }

    public static void main(String[] args) {
        System.out.println(mockLoginTimingsPrep(Arrays.asList(new String[]{"123", "234"}), 4));
    }



}
