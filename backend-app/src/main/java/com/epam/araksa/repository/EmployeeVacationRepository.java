package com.epam.araksa.repository;

import com.epam.araksa.dto.LogTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface EmployeeVacationRepository extends MongoRepository< Map<Integer, String>, Integer> {

}
