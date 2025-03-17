package com.ruanmoraes.student_management_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_grades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double gradeValue;

    @OneToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private EnrollmentModel enrollmentModel;
}
