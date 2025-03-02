package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
