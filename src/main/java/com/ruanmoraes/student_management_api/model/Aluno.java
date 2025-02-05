package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_alunos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "alunoAssociado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Frequencia registroDeFrequencia;
}
