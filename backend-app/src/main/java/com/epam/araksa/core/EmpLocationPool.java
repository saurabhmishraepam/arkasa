/**
 *
 */
package com.epam.araksa.core;

import com.epam.araksa.dto.AppConstantsParkingSlots;
import com.epam.araksa.dto.Dashboard;
import com.epam.araksa.dto.EmpLocationCategoryEnum;
import com.epam.araksa.dto.EmployeeLocation;
import com.epam.araksa.repository.PredictedTimesEmp;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Rakesh_Gupta
 */
@Service
public class EmpLocationPool {

    Map<String, EmployeeLocation> priorityEmployeeLocations = new TreeMap<>();
    private Map<Integer, List<EmployeeLocation>> timeBuckets = new HashMap<>();

    public void updateEmployeeLocation(EmployeeLocation employeeLocation) {
        updateEmployeeLocationPool(employeeLocation);
        prepareEmployeeLocationsBucket(employeeLocation);
    }

    private void prepareEmployeeLocationsBucket(EmployeeLocation emLoc) {
        Long timeToReachOffice = emLoc.getTimeToReachOffice();
        if (timeToReachOffice > 0) {

            int key = (int) (timeToReachOffice / 5);
            if (timeBuckets.get(key * 5) == null) {
                List<EmployeeLocation> empLis = new ArrayList<>();
                empLis.add(emLoc);
                timeBuckets.put(key * 5, empLis);

            } else {
                timeBuckets.get(key * 5).add(emLoc);

            }
        }
    }

    private void updateEmployeeLocationPool(EmployeeLocation employeeLocation) {
        priorityEmployeeLocations.put(employeeLocation.getEmpId(), employeeLocation);
    }


    public Dashboard getEmployeeFromCache(EmployeeLocation employeeLocation) {
        Dashboard dasboardResponseDto = new Dashboard();
        EmployeeLocation employeeLocationFromCache = null;
        System.out.println("I am null " + priorityEmployeeLocations + employeeLocation.getEmpId());
        if (priorityEmployeeLocations.containsKey(employeeLocation.getEmpId())) {
            employeeLocationFromCache = priorityEmployeeLocations.get(employeeLocation.getEmpId());
        }
        if (employeeLocationFromCache == null) {
            updateEmployeeLocation(employeeLocation);
        }
        dasboardResponseDto.setDedicatedSlots(AppConstantsParkingSlots.dedicatedCount);
        dasboardResponseDto.setFloaterSlots(AppConstantsParkingSlots.floaterCount);
        dasboardResponseDto.setLastUpdated(new DateTime().getMillis());
        dasboardResponseDto.setMyRank(getMyLocationRank(employeeLocation));
        dasboardResponseDto.setOnTheWay(priorityEmployeeLocations.size());
        return dasboardResponseDto;
    }

    public int getMyLocationRank(EmployeeLocation employeeLocation) {

        int key = (int) (employeeLocation.getTimeToReachOffice() / 5);
        int keynext = key + 5;
        int countAll = 0;
        for (int i = 5; i < key * 5; i = i + 5) {
            countAll += timeBuckets.containsKey(i) ? timeBuckets.get(i).size() : 0;
        }
        countAll += timeBuckets.containsKey(keynext) ? timeBuckets.get(keynext).stream().filter(emp ->
                emp.getTimeToReachOffice() <= employeeLocation.getTimeToReachOffice()).collect(Collectors.toList()).size() : 0;
        return countAll==0?1:countAll;
    }


    public List<EmployeeLocation> getEmployeeLocationByCategories(EmpLocationCategoryEnum categoryEnum) {
        if (timeBuckets.containsKey(categoryEnum)) {
            return timeBuckets.get(categoryEnum);
        }
        return new ArrayList<>();
    }
}
