package com.ruanmoraes.student_management_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DisciplineRequestDTO {
    private Long id;

    @NotNull(message = "O campo nome é obrigatório")
    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 2, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String name;

    private List<EnrollmentRequestDTO> enrollments;
}
