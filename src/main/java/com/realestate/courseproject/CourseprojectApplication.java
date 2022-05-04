package com.realestate.courseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.realestate.courseproject.repository") //Enabling JPA functionality
@EntityScan("com.realestate.courseproject.model") //Where to search for POJOs (entities)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CourseprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseprojectApplication.class, args);
	}

}
