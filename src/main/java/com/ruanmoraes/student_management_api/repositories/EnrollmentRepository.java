package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.EnrollmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentModel, Long> {
}
