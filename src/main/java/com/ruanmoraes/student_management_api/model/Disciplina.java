package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_disciplinas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professorResponsavel;
}
