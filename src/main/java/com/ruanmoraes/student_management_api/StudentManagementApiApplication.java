package com.ruanmoraes.student_management_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Student Management API",
                version = "1.0.0",
                description = "API for managing students, disciplines, enrollments, and grades."
        )
)
public class StudentManagementApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApiApplication.class, args);
    }
}
