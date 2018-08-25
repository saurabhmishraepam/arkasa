package com.epam.araksa.DummyEmployeePrep;

import com.epam.araksa.dto.LogTime;
import com.epam.araksa.service.HolidayCheckService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.*;

public class VacationDataPrep {

    public static Map<Integer, List<String>> randomUserOnVacc=new HashMap<>();
    public static Map<Integer,String> dateWise=new HashMap<>();

    public static void createMockVacation(List<String> emps, int numberOfMonths){

        float maxLeavePercentage=0.3f;

        emps. forEach(
                emp->{
                    DateTime startDay = new DateTime();
                    DateTime futureDate = new DateTime().plusDays(numberOfMonths);
                    Random rnd = new Random(123);

                    int countOnVaccation=0;
                    List<LogTime> logs = new ArrayList<>();
                    for (; !(startDay.dayOfMonth().get() == futureDate.dayOfMonth().get() &&
                            startDay.monthOfYear().get() == futureDate.monthOfYear().get() && countOnVaccation<(numberOfMonths*maxLeavePercentage));
                         startDay = startDay.plusDays(1)) {

                        HolidayCheckService checkService=new HolidayCheckService();
                        if(checkService.isHoliday()){
                            continue;
                        }
                        if (startDay.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                                startDay.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                            continue;
                        }
                        if(rnd.nextBoolean()){
                            countOnVaccation++;
                            dateWise.put(startDay.dayOfYear().get(),emp);
                            if(randomUserOnVacc.containsKey(startDay.dayOfYear().get())){
                                randomUserOnVacc.get(startDay.dayOfYear().get()).add(emp);
                            }else{
                                randomUserOnVacc.put(startDay.dayOfYear().get(), Arrays.asList(new String[]{emp}));
                            }

                        }

                    }


                }




        );





    }




}
