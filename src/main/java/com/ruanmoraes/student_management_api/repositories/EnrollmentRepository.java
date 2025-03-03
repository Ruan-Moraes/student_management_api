package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Enrollment, Long> {
}
