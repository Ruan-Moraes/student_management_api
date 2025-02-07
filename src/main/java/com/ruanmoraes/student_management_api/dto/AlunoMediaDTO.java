package com.ruanmoraes.student_management_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlunoMediaDTO {
    private String nome;
    private Double media;
}
