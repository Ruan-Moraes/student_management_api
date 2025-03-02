package com.ruanmoraes.student_management_api.dtos.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeRequestDTO {
    private Long id;

    @NotNull(message = "O campo valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O campo valor deve ser no mínimo 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "O campo valor deve ser no máximo 10")
    private Double gradeValue;

    private EnrollmentRequestDTO enrollment;
}
