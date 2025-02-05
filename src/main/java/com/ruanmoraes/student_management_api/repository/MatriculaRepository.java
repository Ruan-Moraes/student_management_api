package com.ruanmoraes.student_management_api.repository;

import com.ruanmoraes.student_management_api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
