package com.ruanmoraes.student_management_api.repository;

import com.ruanmoraes.student_management_api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
