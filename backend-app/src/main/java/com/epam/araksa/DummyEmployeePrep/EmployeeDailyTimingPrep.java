package com.epam.araksa.DummyEmployeePrep;

import com.epam.araksa.dto.ClassifiedWorkingDays;
import com.epam.araksa.dto.LogTime;
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

            HolidayCheckService checkService=new HolidayCheckService();
            if(checkService.isHoliday()){
                continue;
            }
            if (startDay.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                    startDay.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                continue;
            }
            for (String st : emps) {
                float distance = distanceInKms.containsKey(st) ? distanceInKms.get(st) : rnd.nextInt(40);
                int randTimeVarianceInMinutes = rnd.nextInt(5);

                randTimeVarianceInMinutes += 5 * 60;

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
                if (startDay.getDayOfWeek() == DateTimeConstants.SATURDAY)
                    tm.setcWD(ClassifiedWorkingDays.BW);

                logs.add(tm);
            }
        }
        return logs;
    }

    public static void main(String[] args) {
        System.out.println(mockLoginTimingsPrep(Arrays.asList(new String[]{"123", "234"}), 4));
    }



}
