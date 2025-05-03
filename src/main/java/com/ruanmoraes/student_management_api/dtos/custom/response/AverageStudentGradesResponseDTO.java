package com.ruanmoraes.student_management_api.dtos.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class AverageStudentGradesResponseDTO extends RepresentationModel<AverageStudentGradesResponseDTO> {
    private String name;
    private Double average;
}
