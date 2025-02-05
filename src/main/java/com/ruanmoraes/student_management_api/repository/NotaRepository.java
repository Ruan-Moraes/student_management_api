package com.ruanmoraes.student_management_api.repository;

import com.ruanmoraes.student_management_api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
