package com.ruanmoraes.student_management_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_enrollments", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "discipline_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrollmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineModel discipline;

    @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private GradeModel grade;
}
