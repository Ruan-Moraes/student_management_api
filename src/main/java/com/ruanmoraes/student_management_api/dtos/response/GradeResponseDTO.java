package com.ruanmoraes.student_management_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeResponseDTO {
    private Long id;
    private Double gradeValue;
    private EnrollmentResponseDTO enrollment;
}
