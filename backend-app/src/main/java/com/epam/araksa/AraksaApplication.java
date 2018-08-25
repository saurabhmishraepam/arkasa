package com.epam.araksa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@ComponentScan(basePackages= {"com.epam.araksa.*"})
@EnableMongoRepositories(basePackages= {"com.epam.araksa.repository"})
@SpringBootApplication
public class AraksaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AraksaApplication.class, args);
	}
}
