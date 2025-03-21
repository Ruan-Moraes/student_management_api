package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeModel, Long> {
}
