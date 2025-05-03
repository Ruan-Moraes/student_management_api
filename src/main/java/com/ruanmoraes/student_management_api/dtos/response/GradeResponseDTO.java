package com.ruanmoraes.student_management_api.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EnrollmentResponseDTO enrollment;
}
