package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentAverageResponseDTO {
    private String studentName;
    private Double average;
}
