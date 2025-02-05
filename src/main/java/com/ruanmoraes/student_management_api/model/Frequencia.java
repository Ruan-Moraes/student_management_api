package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_frequencias")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer presencas;

    @Column(nullable = false)
    private Integer totalAulas;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno alunoAssociado;
}
