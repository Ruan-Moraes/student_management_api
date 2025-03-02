package com.ruanmoraes.student_management_api.controllers;

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
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentById(id);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.createStudent(studentRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO student = studentService.updateById(id, studentRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "text/plain")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Student with id " + id + " deleted successfully.");
    }

    @GetMapping("/low-frequency/{frequency}")
    public ResponseEntity<?> listStudentsByLowFrequency(@PathVariable Double frequency) {
        List<StudentResponseDTO> students = studentService.listStudentsByLowFrequency(frequency);

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}
