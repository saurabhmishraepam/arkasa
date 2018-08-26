package com.epam.araksa.repository;

import com.epam.araksa.dto.PredictedTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictedTimesEmp extends MongoRepository<PredictedTime, Long
        > {

    @Query("{empId:'?0'}")
    List<PredictedTime> findByEmpId(String empId);


    @Query("{_id:'?0'}")
    PredictedTime findBy_id(String _id);

    List<PredictedTime> findByForDateBetween(String start, String end);

}

