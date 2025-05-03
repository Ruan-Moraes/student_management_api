package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.custom.response.*;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
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

    @GetMapping(value = "/findAllGradesByStudentId/{studentId}", produces = "application/json")
    public ResponseEntity<StudentGradesResponseDTO> findAllGradesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.status(200).body(gradeService.findAllGradesByStudentId(studentId));
    }

    @GetMapping(value = "/findAverageForEachStudent", produces = "application/json")
    public ResponseEntity<List<AverageStudentGradesResponseDTO>> findAverageForEachStudent() {
        return ResponseEntity.status(200).body(gradeService.findAverageForEachStudent());
    }

    @GetMapping(value = "/findAverageStudentById/{studentId}", produces = "application/json")
    public ResponseEntity<AverageStudentGradesResponseDTO> findAverageStudentById(@PathVariable Long studentId) {
        return ResponseEntity.status(200).body(gradeService.findAverageStudentById(studentId));
    }

    @GetMapping(value = "/findAboveAverageStudents", produces = "application/json")
    public ResponseEntity<List<AverageStudentGradesResponseDTO>> findAboveAverageStudents() {
        return ResponseEntity.status(200).body(gradeService.findAboveAverageStudents());
    }

    @GetMapping(value = "/calculateAverageAllGrades", produces = "application/json")
    public ResponseEntity<AverageOfAllGradesResponseDTO> calculateAverageAllGrades() {
        return ResponseEntity.status(200).body(gradeService.calculateAverageAllGrades());
    }

    @GetMapping(value = "/averageGradesByDiscipline", produces = "application/json")
    public ResponseEntity<AverageByDisciplineResponseDTO> calculateAverageGradesByDiscipline() {
        return ResponseEntity.status(200).body(gradeService.calculateAverageGradesByDiscipline());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<GradeResponseDTO> create
            (
                    @RequestParam(value = "studentId", required = true) Long studentId,
                    @RequestParam(value = "disciplineId", required = true) Long disciplineId,
                    @Valid @RequestBody GradeRequestDTO gradeRequestDTO
            ) {
        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.findByStudentIdAndDisciplineId(studentId, disciplineId);

        return ResponseEntity.status(201).body(gradeService.create(enrollmentResponseDTO, gradeRequestDTO));
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<GradeResponseDTO> update(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "disciplineId", required = true) Long disciplineId,
            @Valid @RequestBody GradeRequestDTO gradeRequestDTO
    ) {
        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.findByStudentIdAndDisciplineId(studentId, disciplineId);

        return ResponseEntity.status(200).body(gradeService.update(enrollmentResponseDTO, gradeRequestDTO));
    }
}
