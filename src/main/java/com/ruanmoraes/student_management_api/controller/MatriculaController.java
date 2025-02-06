package com.ruanmoraes.student_management_api.controller;

import com.ruanmoraes.student_management_api.model.Matricula;
import com.ruanmoraes.student_management_api.service.MatriculaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }


    @PostMapping("/criar")
    public Matricula criarMatricula(@RequestBody Matricula matricula) {
        return matriculaService.criarMatricula(matricula);
    }

    public void matricularAluno(Long alunoId, Long disciplinaId) {
        // Lógica para matricular aluno
    }

    public void desmatricularAluno(Long alunoId, Long disciplinaId) {
        // Lógica para desmatricular aluno
    }
}
