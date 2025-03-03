package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Grade, Long> {
}
