/**
 * 
 */
package com.epam.araksa.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.epam.araksa.dto.EmpLocationCategoryEnum;
import com.epam.araksa.dto.EmployeeLocation;

/**
 * @author Rakesh_Gupta
 *
 */
@Service
public class EmpLocationPool {

	List<EmployeeLocation> emList;
	Map<String, EmployeeLocation> priorityEmployeeLocations;
	private static int MAX_EMP_POOL_SIZE;
	private Map<EmpLocationCategoryEnum, List<EmployeeLocation>> map;

	public EmpLocationPool(List<EmployeeLocation> emList) {
		this();
		this.emList = emList;
		prepareEmployeeLocationsBuckets(this.emList);
	}

	public EmpLocationPool() {

		priorityEmployeeLocations = new TreeMap<>();
	}

	public void updateEmployeeLocation(EmployeeLocation employeeLocation) {
		updateEmployeeLocationPool(employeeLocation);
		prepareEmployeeLocationsBucket(employeeLocation);

	}

	private void prepareEmployeeLocationsBuckets(List<EmployeeLocation> emList) {
		for (EmployeeLocation EmployeeLocation : emList) {
			if (EmployeeLocation != null) {
				prepareEmployeeLocationsBucket(EmployeeLocation);
			}
		}
	}

	private void prepareEmployeeLocationsBucket(EmployeeLocation emLoc) {
		Long timeToReachOffice = emLoc.getTimeToReachOffice();
		if (timeToReachOffice > 0) {
			if (timeToReachOffice < 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_1_MIN).add(emLoc);
			} else if (timeToReachOffice < 5 * 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_5_MIN).add(emLoc);
			} else if (timeToReachOffice < 10 * 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_10_MIN).add(emLoc);
			} else if (timeToReachOffice < 15 * 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_15_MIN).add(emLoc);
			} else if (timeToReachOffice < 20 * 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_20_MIN).add(emLoc);
			} else if (timeToReachOffice < 25 * 60) {
				map.get(EmpLocationCategoryEnum.EMP_LIST_WITHIN_25_MIN).add(emLoc);
			}
		}
	}

	private void updateEmployeeLocationPool(EmployeeLocation employeeLocation) {
		priorityEmployeeLocations.put(employeeLocation.getEmpId(), employeeLocation);
	}

	public List<EmployeeLocation> getTopPriorityEmployeeLocation(int size) {
		List<EmployeeLocation> list = new ArrayList<>();

		if (size >= emList.size()) {
			return emList;
		}

		return list;
	}

	public List<EmployeeLocation> getEmployeeLocationByCategories(EmpLocationCategoryEnum categoryEnum) {
		if (map.containsKey(categoryEnum)) {
			return map.get(categoryEnum);
		}
		return new ArrayList<>();
	}
}
