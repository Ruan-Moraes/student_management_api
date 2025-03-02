package com.ruanmoraes.student_management_api.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisciplineResponseDTO {
    private Long id;
    private String name;
    private List<EnrollmentResponseDTO> enrollments;
}
