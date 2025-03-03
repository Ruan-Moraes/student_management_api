package com.ruanmoraes.student_management_api.repositories;

import com.ruanmoraes.student_management_api.models.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
