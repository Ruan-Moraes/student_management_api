package com.ruanmoraes.student_management_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
//    private final MatriculaService matriculaService;
//
//    public MatriculaController(MatriculaService matriculaService) {
//        this.matriculaService = matriculaService;
//    }
//
//    @PostMapping("/criar")
//    public ResponseEntity<Matricula> criarMatricula(
//            @RequestBody Matricula matricula
//    ) {
//        Matricula matriculaCriada = matriculaService.criarMatricula(matricula);
//
//
//        return ResponseEntity.ok(matriculaCriada);
//    }
//
//    @DeleteMapping("/remover/{id}")
//    public ResponseEntity<String> removerMatricula(
//            @PathVariable Long id
//    ) {
//        boolean isDeleted = matriculaService.removerMatricula(id);
//
//        if (isDeleted) {
//            return ResponseEntity.ok("Matrícula removida com sucesso");
//        }
//
//        return ResponseEntity.notFound().build();
//    }
}
