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

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();

        return ResponseEntity.status(200).body(students);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentById(id);

        return ResponseEntity.status(200).body(student);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.createStudent(studentRequestDTO);

        return ResponseEntity.status(201).body(student);
    }


    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentResponseDTO> updateById(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.updateById(studentRequestDTO);

        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/low-frequency")
    public ResponseEntity<List<StudentResponseDTO>> listStudentsByLowFrequency(@RequestParam("frequency_below") Double frequency) {
        List<StudentResponseDTO> students = studentService.listStudentsByLowFrequency(frequency);

        return ResponseEntity.status(200).body(students);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
