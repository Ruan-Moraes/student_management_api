package com.ruanmoraes.student_management_api.controller;

import com.ruanmoraes.student_management_api.model.Aluno;
import com.ruanmoraes.student_management_api.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno alunoFrequencia) {
        Aluno aluno = alunoService.cadastrarAluno(alunoFrequencia);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listarTodosAlunos() {
        List<Aluno> alunos = alunoService.buscarTodosAlunos();

        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAlunoPorId(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.atualizarAlunoPorId(id, aluno);

        if (alunoAtualizado != null) {
            return ResponseEntity.ok(alunoAtualizado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAluno(@PathVariable Long id) {
        boolean isDeleted = alunoService.deletarAlunoPorId(id);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
    }

    @GetMapping("/buscar/frequencia/{frequencia}")
    public ResponseEntity<List<Aluno>> listarAlunosComFrequenciaBaixa(@PathVariable Double frequencia) {
        List<Aluno> alunos = alunoService.listarAlunosComFrequenciaBaixa(frequencia);

        return ResponseEntity.ok(alunos);
    }
}
