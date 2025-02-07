package com.ruanmoraes.student_management_api.controller;

import com.ruanmoraes.student_management_api.model.Matricula;
import com.ruanmoraes.student_management_api.service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Matricula> criarMatricula(
            @RequestBody Matricula matricula
    ) {
        Matricula matriculaCriada = matriculaService.criarMatricula(matricula);

        return ResponseEntity.ok(matriculaCriada);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> removerMatricula(
            @PathVariable Long id
    ) {
        boolean isDeleted = matriculaService.removerMatricula(id);

        if (isDeleted) {
            return ResponseEntity.ok("Matrícula removida com sucesso");
        }

        return ResponseEntity.notFound().build();
    }
}
