package com.ruanmoraes.student_management_api.controllers.impl;

import com.ruanmoraes.student_management_api.controllers.StudentControllerDocs;
import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController implements StudentControllerDocs {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(studentRequestDTO));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> updateById(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateById(id, studentRequestDTO));
    }

    @GetMapping(value = "/lowFrequency", produces = "application/json")
    public ResponseEntity<List<StudentResponseDTO>> findByLowFrequency(@RequestParam("frequency_below") Double frequency) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findByLowFrequency(frequency));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
