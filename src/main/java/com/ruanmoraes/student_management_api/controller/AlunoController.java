package com.ruanmoraes.student_management_api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    // Adicionar aluno
    @PostMapping("/add")
    public String addAluno() {
        return "Aluno adicionado";
    }

    // Listar alunos
    @PostMapping("/list")
    public String listAlunos() {
        return "Alunos listados";
    }

    // Buscar aluno por nome
    @PostMapping("/search")
    public String searchAluno() {
        return "Aluno encontrado";
    }

    // Atualizar aluno
    @PutMapping("/update")
    public String updateAluno() {
        return "Aluno atualizado";
    }

    // Deletar aluno
    @DeleteMapping("/delete")
    public String deleteAluno() {
        return "Aluno deletado";
    }
}
