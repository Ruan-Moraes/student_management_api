package com.ruanmoraes.student_management_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DisciplineResponseDTO extends RepresentationModel<DisciplineResponseDTO> {
    private Long id;
    private String name;
    private List<EnrollmentResponseDTO> enrollments;
}
