package com.ruanmoraes.student_management_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotaDTO {
    private Long id;
    private Long matricula;
    private String aluno;
    private String disciplina;
    private Double valorNota;
}
