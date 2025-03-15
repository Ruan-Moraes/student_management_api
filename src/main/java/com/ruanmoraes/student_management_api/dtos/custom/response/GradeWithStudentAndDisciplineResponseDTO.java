package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GradeWithStudentAndDisciplineResponseDTO {
    Long id;
    String studentName;
    String disciplineName;
    Double gradeValue;
}
