package com.ruanmoraes.student_management_api.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentRequestDTO {
    private Long id;
    private Long studentId;
    private Long disciplineId;
//    private GradeRequestDTO grade;
}
