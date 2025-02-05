package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_notas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valorNota;

    @OneToOne(mappedBy = "notaAluno")
    private Matricula matriculaAssociada;
}
