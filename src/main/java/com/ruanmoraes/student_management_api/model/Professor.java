package com.ruanmoraes.student_management_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_professores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "professorResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disciplina> disciplinasMinistradas;
}
