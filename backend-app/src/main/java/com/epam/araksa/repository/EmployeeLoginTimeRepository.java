package com.epam.araksa.repository;

import com.epam.araksa.dto.LogTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeLoginTimeRepository extends MongoRepository<LogTime, String> {
}
