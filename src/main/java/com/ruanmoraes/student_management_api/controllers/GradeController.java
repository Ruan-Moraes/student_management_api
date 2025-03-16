package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageByDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.StudentGradesResponseDTO;
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

    @GetMapping(value = "/{studentId}", produces = "application/json")
    public ResponseEntity<StudentGradesResponseDTO> findAllGradesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.status(200).body(gradeService.findAllGradesByStudentId(studentId));
    }

    @GetMapping(value = "/calculateAverageAllGrade", produces = "application/json")
    public ResponseEntity<AvarageResponseDTO> calculateAverageAllGrade() {
        return ResponseEntity.status(200).body(gradeService.calculateAverageAllGrade());
    }

    @GetMapping(value = "/calculateAvarageAllGradeByDiscipline", produces = "application/json")
    public ResponseEntity<AvarageByDisciplineResponseDTO> calculateAvarageAllGradeByDiscipline() {
        return ResponseEntity.status(200).body(gradeService.calculateAvarageAllGradeByDiscipline());
    }

    @GetMapping(value = "/avarage/{studentId}", produces = "application/json")
    public ResponseEntity<AvarageResponseDTO> averageGradeByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.status(200).body(gradeService.averageGradeByStudentId(studentId));
    }

    @GetMapping(value = "/findAboveAverageStudents", produces = "application/json")
    public ResponseEntity<List<GradeWithStudentAndDisciplineResponseDTO>> findAboveAverageStudents() {
        return ResponseEntity.status(200).body(gradeService.findAboveAverageStudents());
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
}
