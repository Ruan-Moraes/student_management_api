package com.ruanmoraes.student_management_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
//    private final DisciplinaService disciplinaService;
//
//    public DisciplinaController(DisciplinaService disciplinaService) {
//        this.disciplinaService = disciplinaService;
//    }
//
//    @PostMapping("/cadastrar")
//    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
//        Disciplina disciplinaCadastrada = disciplinaService.cadastrarDisciplina(disciplina);
//
//        return ResponseEntity.ok(disciplinaCadastrada);
//    }
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<Disciplina>> listarTodasDisciplinas() {
//        List<Disciplina> disciplinas = disciplinaService.listarTodasDisciplinas();
//
//        return ResponseEntity.ok(disciplinas);
//    }
//
//    @PutMapping("/atualizar/{id}")
//    public ResponseEntity<Disciplina> atualizarDisciplinaPorId(@PathVariable Long id, @RequestBody Disciplina disciplina) {
//        Disciplina disciplinaAtualizada = disciplinaService.atualizarDisciplinaPorId(id, disciplina);
//
//        if (disciplinaAtualizada != null) {
//            return ResponseEntity.ok(disciplinaAtualizada);
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//
//    @DeleteMapping("/deletar/{id}")
//    public ResponseEntity<Disciplina> deletarDisciplinaPorId(@PathVariable Long id) {
//        boolean isDeleted = disciplinaService.deletarDisciplinaPorId(id);
//
//        if (isDeleted) {
//            return ResponseEntity.status(HttpStatus.OK).body(null);
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
}
