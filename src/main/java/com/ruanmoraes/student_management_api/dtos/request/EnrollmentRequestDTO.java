package com.ruanmoraes.student_management_api.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentRequestDTO {
    private Long id;

    @Positive(message = "O campo valor deve ser maior que 0")
    @NotNull(message = "O campo valor é obrigatório")
    private Long studentId;

    @Positive(message = "O campo valor deve ser maior que 0")
    @NotNull(message = "O campo valor é obrigatório")
    private Long disciplineId;
//    private GradeRequestDTO grade;
}
