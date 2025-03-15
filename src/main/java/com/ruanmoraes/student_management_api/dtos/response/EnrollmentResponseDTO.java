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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnrollmentResponseDTO extends RepresentationModel<EnrollmentResponseDTO> {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long disciplineId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GradeResponseDTO grade;
}
