package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_matriculas_alunos_disciplinas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplinaMatriculada;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno alunoMatriculado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nota_id", nullable = false)
    private Nota notaAluno;
}
