package com.epam.araksa.datacache;

import com.epam.araksa.dto.EmployeeLocation;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class InActiveEmployeeCache {
    private static LoadingCache<String, EmployeeLocation> employeeCache;

    static {
        System.out.println("invoking static block");
        employeeCache =
                CacheBuilder.newBuilder()
                        .maximumSize(400)
                        .expireAfterAccess(24, TimeUnit.HOURS)      // cache will expire after 30 minutes of access
                        .build(new CacheLoader<String, EmployeeLocation>() {

                            @Override
                            public EmployeeLocation load(String uuid) throws Exception {
                                //make the expensive call
                                EmployeeLocation emp = new EmployeeLocation();
                                emp.setId("init-ID");
                                return emp;
                            }
                        });
    }


    public LoadingCache<String, EmployeeLocation> getCache() {
        return employeeCache;
    }


    public void addToCache(EmployeeLocation employeeLocation) {
        employeeCache.asMap().put(employeeLocation.getId(), employeeLocation);
    }

    public void deleteFromCache(EmployeeLocation employeeLocation) {
        employeeCache.asMap().remove(employeeLocation.getId());
    }

    public EmployeeLocation getFromCache(String uuid) {
        return employeeCache.asMap().get(uuid);
    }

    public void refreshCache(String uuid) {
        employeeCache.refresh(uuid);
    }


}
