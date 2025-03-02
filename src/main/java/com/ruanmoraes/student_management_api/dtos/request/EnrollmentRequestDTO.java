package com.ruanmoraes.student_management_api.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnrollmentRequestDTO {
    private Long id;
    private StudentRequestDTO student;
    private DisciplineRequestDTO discipline;
    private GradeRequestDTO grade;
}
