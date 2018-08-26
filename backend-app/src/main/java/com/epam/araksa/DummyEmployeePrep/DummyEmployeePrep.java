package com.epam.araksa.DummyEmployeePrep;

import com.epam.araksa.dto.Employee;
import com.epam.araksa.dto.JobFunction;
import com.epam.araksa.dto.ParkType;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DummyEmployeePrep {
    static List<String> firstNames = Arrays.asList(new String[]
            {"Saurabh",
                    "Gowtham", "Rakesh", "Sana", "Vamsi",
                    "Prakash", "Kunal"});
    static List<String> surname = Arrays.asList(new String[]{
            "Mishra",
            "Gupta",
            "Kumar",
            "Dutta",
            "Soni",
            "Das"
    });

    static List<String> jobLevel = Arrays.asList(new String[]{
            "D1", "D2", "D3", "C3", "C2", "C1", "B1", "B2", "B3", "A1", "A2", "A3"
    });

    static List<String> projects = Arrays.asList(new String[]{
            "MACD-GDPR", "MACD-ABCD", "EPM-ESAG", "SWA-4489", "MBZK-CONS", "HCB-OTW1", "EPM-ITMR"

    });

    public static List<String> empIds=new ArrayList<>();

    public static int EMPID_SEQ = 530000;

    public static List<Employee> prepDummyEmployes(int numberOf) {
        Random rnd = new Random(123);
        int dedPer = (int) (0.7 * numberOf);
        System.out.println(dedPer);
        List<Employee> listEmp = new ArrayList<>();

        for (int i = 1; i <= numberOf; i++) {
            Employee emp = new Employee();
            emp.setEmpId(EMPID_SEQ + (i * 2000) + "");
            empIds.add(emp.getEmpId());
            int sizeF = i % firstNames.size();
            int sizeL = i % surname.size();
            emp.setEmpName(firstNames.get(sizeF) + " " + surname.get(sizeL));
            emp.setParkType(i < dedPer ? ParkType.DEDICATED : ParkType.FLOATTER);
            emp.setJobLevel(jobLevel.get(rnd.nextInt(jobLevel.size())));
            emp.setIsActive(true);
            emp.setProjectTag(projects.get(rnd.nextInt(projects.size())));
            emp.setDateRegistered(new DateTime().getMillis());
            emp.setjFun(JobFunction.values()[rnd.nextInt(JobFunction.values().length)]);
            listEmp.add(emp);
        }
        System.out.println(listEmp);
        return listEmp;
    }

    public static void main(String[] args) {

        prepDummyEmployes(30);
    }


}
