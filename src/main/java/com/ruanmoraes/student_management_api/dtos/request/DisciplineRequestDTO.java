package com.ruanmoraes.student_management_api.dtos.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisciplineRequestDTO {
    private Long id;

    @NotNull(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String name;

    private List<EnrollmentRequestDTO> enrollments;
}
