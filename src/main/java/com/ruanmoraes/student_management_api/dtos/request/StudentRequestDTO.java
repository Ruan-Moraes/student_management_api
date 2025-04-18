package com.ruanmoraes.student_management_api.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDTO {
    private Long id;

    @NotNull(message = "O campo nome é obrigatório")
    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotNull(message = "O campo percentual de frequência é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O campo percentual de frequência deve ser no mínimo 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "O campo percentual de frequência deve ser no máximo 100")
    private Double frequency;

    private List<EnrollmentRequestDTO> enrollments;
}
