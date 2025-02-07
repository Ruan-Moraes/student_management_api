package com.ruanmoraes.student_management_api.controller;

import com.ruanmoraes.student_management_api.dto.AlunoMediaDTO;
import com.ruanmoraes.student_management_api.dto.DisciplinaDTO;
import com.ruanmoraes.student_management_api.dto.NotaDTO;
import com.ruanmoraes.student_management_api.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping("/alunos")
    public List<NotaDTO> buscarTodasNotasAlunos() {
        return notaService.buscarTodasNotasAlunos();
    }

    @GetMapping("/alunos/{alunoId}")
    public List<NotaDTO> buscarTodasNotasAlunoPorId(@PathVariable Long alunoId) {
        return notaService.buscarTodasNotasAlunoPorId(alunoId);
    }

    @GetMapping("/media-todos-alunos")
    public ResponseEntity<Double> calcularMediaNotasTodosAlunos() {
        Double media = notaService.calcularMediaNotasTodosAlunos();

        return ResponseEntity.ok(media);
    }

    @GetMapping("/media/{alunoId}")
    public ResponseEntity<Double> calcularMediaNotasAlunoPorId(@PathVariable Long alunoId) {
        Double media = notaService.calcularMediaNotasAlunoPorId(alunoId);

        return ResponseEntity.ok(media);
    }

    @GetMapping("/media-todos-alunos-disciplina/")
    public ResponseEntity<List<DisciplinaDTO>> calcularMediaNotasTodosAlunosDisciplina() {
        List<DisciplinaDTO> disciplinas = notaService.calcularMediaTodosAlunosDisciplina();

        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/alunos-acima-media-turma")
    public List<AlunoMediaDTO> buscarAlunosAcimaMediaTurma() {
        return notaService.buscarAlunosAcimaMediaTurma();
    }

    @PutMapping("/atualizar/{alunoId}/{disciplinaId}")
    public ResponseEntity<NotaDTO> atualizarNotaPorAlunoIdEDisciplinaId(
            @PathVariable Long alunoId,
            @PathVariable Long disciplinaId,
            @RequestBody NotaDTO notaDTO
    ) {
        NotaDTO notaAtualizada = notaService.atualizarNotaPorAlunoIdEDisciplinaId(alunoId, disciplinaId, notaDTO);

        if (notaAtualizada != null) {
            return ResponseEntity.ok(notaAtualizada);
        }

        return ResponseEntity.notFound().build();
    }
}
