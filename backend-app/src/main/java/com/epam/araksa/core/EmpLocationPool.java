/**
 * 
 */
package com.epam.araksa.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.araksa.dto.EmpLocationCategoryEnum;
import com.epam.araksa.dto.EmployeeLocation;
import com.google.common.collect.MinMaxPriorityQueue;

/**
 * @author Rakesh_Gupta
 *
 */
public class EmpLocationPool {

	List<EmployeeLocation> emList;
	MinMaxPriorityQueue<EmployeeLocation> priorityEmployeeLocations;
	private static int MAX_EMP_SIZE;
	private Map<EmpLocationCategoryEnum, List<EmployeeLocation>> map;

	public EmpLocationPool(List<EmployeeLocation> emList) {
		this();
		this.emList = emList;
		priorityEmployeeLocations.addAll(this.emList);
		map = new HashMap<>();
		for (EmpLocationCategoryEnum eCategoryEnum : EmpLocationCategoryEnum.values()) {
			map.put(eCategoryEnum, new ArrayList<EmployeeLocation>());
		}
		prepareEmployeeLocationsBuckets(this.emList);
	}

	public EmpLocationPool() {
		MAX_EMP_SIZE = 1000;
		this.priorityEmployeeLocations = MinMaxPriorityQueue.orderedBy(new EmpLocationComparator())
				.maximumSize(MAX_EMP_SIZE).create();
	}

	public Boolean updateEmployeeLocation(EmployeeLocation employeeLocation) {
		updateEmployeeLocationPool(employeeLocation);
		prepareEmployeeLocationsBucket(employeeLocation);
		return emList.add(employeeLocation);
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
		if (priorityEmployeeLocations.contains(employeeLocation)) {
			priorityEmployeeLocations.add(employeeLocation);
		}
	}

	public List<EmployeeLocation> getTopPriorityEmployeeLocation(int size) {
		List<EmployeeLocation> list = new ArrayList<>();

		if (size >= emList.size()) {
			return emList;
		}

		for (int i = 0; i < size; i++) {
			EmployeeLocation empLoc = this.priorityEmployeeLocations.pollFirst();
			list.add(empLoc);
		}

		this.priorityEmployeeLocations.addAll(list);
		return list;
	}

	public List<EmployeeLocation> getEmployeeLocationByCategories(EmpLocationCategoryEnum categoryEnum) {
		if (map.containsKey(categoryEnum)) {
			return map.get(categoryEnum);
		}
		return new ArrayList<>();
	}
}
