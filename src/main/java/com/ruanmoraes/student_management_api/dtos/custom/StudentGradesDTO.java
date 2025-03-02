package com.ruanmoraes.student_management_api.dtos.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentGradesDTO {
    private String studentName;
    private Double average;
}
