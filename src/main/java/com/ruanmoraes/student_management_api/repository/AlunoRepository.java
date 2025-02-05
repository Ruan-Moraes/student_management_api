package com.ruanmoraes.student_management_api.repository;

import com.ruanmoraes.student_management_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
