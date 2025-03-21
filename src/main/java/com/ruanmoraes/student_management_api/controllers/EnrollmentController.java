package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.EnrollmentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EnrollmentResponseDTO>> findAll() {
        return ResponseEntity.status(200).body(enrollmentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EnrollmentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(enrollmentService.findById(id));
    }

    @GetMapping(value = "FindByStudentIdAndDisciplineId", produces = "application/json")
    public ResponseEntity<EnrollmentResponseDTO> findByStudentIdAndDisciplineId(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "disciplineId", required = true) Long disciplineId) {
        return ResponseEntity.status(200).body(enrollmentService.findByStudentIdAndDisciplineId(studentId, disciplineId));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EnrollmentResponseDTO> create(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        return ResponseEntity.status(201).body(enrollmentService.create(enrollmentRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        enrollmentService.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
