package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class StudentGradesResponseDTO {
    private String studentName;
    private Map<String, Double> grades;
}
