package com.ruanmoraes.student_management_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private Double media;
}
