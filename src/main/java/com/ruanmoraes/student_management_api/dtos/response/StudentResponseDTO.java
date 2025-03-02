package com.ruanmoraes.student_management_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponseDTO extends RepresentationModel<StudentResponseDTO> {
    private Long id;
    private String name;
    private Double frequency;
    private List<EnrollmentResponseDTO> enrollments;
}
