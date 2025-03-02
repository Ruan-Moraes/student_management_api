package com.ruanmoraes.student_management_api.dtos.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDTO {
    private Long id;

    @NotNull(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotNull(message = "O campo percentual de frequência é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O campo percentual de frequência deve ser no mínimo 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "O campo percentual de frequência deve ser no máximo 100")
    private Double frequency;

    private List<EnrollmentRequestDTO> enrollments;
}
