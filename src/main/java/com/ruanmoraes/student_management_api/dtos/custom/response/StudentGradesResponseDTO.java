package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

@AllArgsConstructor
@Getter
public class StudentGradesResponseDTO extends RepresentationModel<StudentGradesResponseDTO> {
    private Long studentId;
    private String studentName;
    private Map<String, Double> grades;
}
