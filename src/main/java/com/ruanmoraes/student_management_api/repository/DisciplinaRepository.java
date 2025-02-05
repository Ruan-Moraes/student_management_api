package com.ruanmoraes.student_management_api.repository;

import com.ruanmoraes.student_management_api.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
