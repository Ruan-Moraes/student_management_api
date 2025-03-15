package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.StudentGradesResponseDTO;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.mappers.EnrollmentMapper;
import com.ruanmoraes.student_management_api.models.Enrollment;
import com.ruanmoraes.student_management_api.services.EnrollmentService;
import com.ruanmoraes.student_management_api.services.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;
    private final EnrollmentService enrollmentService;

    public GradeController(GradeService gradeService, EnrollmentService enrollmentService) {
        this.gradeService = gradeService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<GradeWithStudentAndDisciplineResponseDTO>> findAll() {
        return ResponseEntity.status(200).body(gradeService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentGradesResponseDTO> findAllGradesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.status(200).body(gradeService.findAllGradesByStudentId(studentId));
    }

    //
//    @GetMapping("/media-todos-alunos")
//    public ResponseEntity<Double> calcularMediaNotasTodosAlunos() {
//        Double media = notaService.calcularMediaNotasTodosAlunos();
//
//        return ResponseEntity.ok(media);
//    }
//
//    @GetMapping("/media/{alunoId}")
//    public ResponseEntity<Double> calcularMediaNotasAlunoPorId(@PathVariable Long alunoId) {
//        Double media = notaService.calcularMediaNotasAlunoPorId(alunoId);
//
//        return ResponseEntity.ok(media);
//    }
//
//    @GetMapping("/media-todos-alunos-disciplina/")
//    public ResponseEntity<List<DisciplinaDTO>> calcularMediaNotasTodosAlunosDisciplina() {
//        List<DisciplinaDTO> disciplinas = notaService.calcularMediaTodosAlunosDisciplina();
//
//        return ResponseEntity.ok(disciplinas);
//    }
//
//    @GetMapping("/alunos-acima-media-turma")
//    public List<StudentGradesView> buscarAlunosAcimaMediaTurma() {
//        return notaService.buscarAlunosAcimaMediaTurma();
//    }
//
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<GradeResponseDTO> create
    (
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "disciplineId", required = true) Long disciplineId,
            @Valid @RequestBody GradeRequestDTO gradeRequestDTO
    ) {
        Enrollment enrollment = EnrollmentMapper.INSTANCE.toModel(enrollmentService.findByStudentIdAndDisciplineId(studentId, disciplineId));

        GradeResponseDTO gradeResponseDTO = gradeService.create(enrollment, gradeRequestDTO);

        return ResponseEntity.status(201).body(gradeResponseDTO);
    }
}
