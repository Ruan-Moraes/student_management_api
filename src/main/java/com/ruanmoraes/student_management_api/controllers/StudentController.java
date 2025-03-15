package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentResponseDTO>> findAll() {
        return ResponseEntity.status(200).body(studentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(studentService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(201).body(studentService.create(studentRequestDTO));
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> updateById(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(200).body(studentService.updateById(studentRequestDTO));
    }

    @GetMapping(value = "/low-frequency")
    public ResponseEntity<List<StudentResponseDTO>> findByLowFrequency(@RequestParam("frequency_below") Double frequency) {
        return ResponseEntity.status(200).body(studentService.listByLowFrequency(frequency));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
