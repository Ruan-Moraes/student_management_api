package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.services.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DisciplineResponseDTO>> findAll() {
        return ResponseEntity.status(200).body(disciplineService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(disciplineService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> create(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        return ResponseEntity.status(201).body(disciplineService.create(disciplineRequestDTO));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> updateById(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        return ResponseEntity.status(200).body(disciplineService.updateById(disciplineRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        disciplineService.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
