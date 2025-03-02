package com.ruanmoraes.student_management_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnrollmentResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private DisciplineResponseDTO discipline;
    private GradeResponseDTO grade;
}
