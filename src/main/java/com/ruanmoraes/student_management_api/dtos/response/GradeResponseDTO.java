package com.ruanmoraes.student_management_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeResponseDTO extends RepresentationModel<GradeResponseDTO> {
    private Long id;
    private Double gradeValue;
    private EnrollmentResponseDTO enrollment;
}
