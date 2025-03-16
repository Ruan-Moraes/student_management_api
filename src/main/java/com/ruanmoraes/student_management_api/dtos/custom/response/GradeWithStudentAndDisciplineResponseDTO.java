package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class GradeWithStudentAndDisciplineResponseDTO extends RepresentationModel<GradeWithStudentAndDisciplineResponseDTO> {
    Long id;
    String studentName;
    String disciplineName;
    Double gradeValue;
}
