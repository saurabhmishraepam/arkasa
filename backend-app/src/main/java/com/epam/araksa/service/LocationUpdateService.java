package com.epam.araksa.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.araksa.datacache.ActiveEmployeeCache;
import com.epam.araksa.datacache.InActiveEmployeeCache;
import com.epam.araksa.dto.EmployeeLocation;
import com.epam.araksa.dto.LocationUpdaterServiceResponse;

@Service
public class LocationUpdateService {

	@Autowired
	InActiveEmployeeCache cache;
	@Autowired
	VacationService vacationService;
	@Autowired
	ActiveEmployeeCache activeEmployeeCache;
	private static final int CRON_USER_ACTIVE_INTERVAL = 5;
	private static final int CRON_USER_INACTIVE_INTERVAL = 15;
	private static final int DISTANCE_VARIANCE = 2;
	private static final int NUMBER_CHECK_ENOUGH = 2;

	public LocationUpdaterServiceResponse locationUpdater(EmployeeLocation empLocation) {

		// leave service -- get the employee status
		//
		LocationUpdaterServiceResponse locationRes = new LocationUpdaterServiceResponse();
		locationRes.setUuid(empLocation.getId());

		if (!vacationService.isOnVacationTodayByEmpId(empLocation.getEmpId())) {
			locationRes.setActive(true);
			EmployeeLocation previousLocation = cache.getFromCache(empLocation.getId());

			if (previousLocation != null) {
				if (empLocation.getDistanceCurrentInKms() + DISTANCE_VARIANCE < previousLocation
						.getDistanceCurrentInKms()) {
					// moving as 15mnts
					// update recheck flag to 5mnts check
					if (previousLocation.getUpdateCount() >= NUMBER_CHECK_ENOUGH) {
						locationRes.setMovingTowardsOffice(true);
					} else {
						locationRes.setMovingTowardsOffice(false);
					}
					empLocation.setUpdateCount(previousLocation.getUpdateCount() + 1);
					empLocation.setLastUpdated(new DateTime().getMillis());
					cache.deleteFromCache(empLocation);
					cache.addToCache(empLocation);
					locationRes.setRecheckcounter(CRON_USER_ACTIVE_INTERVAL);
					activeEmployeeCache.enqueue(empLocation);
					System.out.println(ActiveEmployeeCache.activeEmployeeCache.size());
				} else {
					locationRes.setMovingTowardsOffice(false);
					cache.deleteFromCache(empLocation);
					cache.addToCache(empLocation);
					locationRes.setRecheckcounter(CRON_USER_INACTIVE_INTERVAL);
				}
			} else {
				locationRes.setMovingTowardsOffice(false);
				cache.deleteFromCache(empLocation);
				cache.addToCache(empLocation);
				locationRes.setRecheckcounter(CRON_USER_INACTIVE_INTERVAL);
			}
			// Put in priorityEmployeeList for getting topPriorityEmpLocation list
			
		} else {
			// send flag to deactive check for the user for the day
			locationRes.setActive(false);
		}
		return locationRes;
	}
}
