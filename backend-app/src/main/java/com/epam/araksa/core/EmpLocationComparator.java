package com.epam.araksa.core;

import java.util.Comparator;

import com.epam.araksa.dto.EmployeeLocation;

/**
 * @author Rakesh_Gupta
 *
 */
public class EmpLocationComparator implements Comparator<EmployeeLocation> {

	@Override
	public int compare(EmployeeLocation emp1, EmployeeLocation emp2) {
		return (int) (emp1.getTimeToReachOffice() - emp2.getTimeToReachOffice());
	}
}
