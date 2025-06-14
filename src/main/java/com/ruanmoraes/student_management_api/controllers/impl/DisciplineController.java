package com.ruanmoraes.student_management_api.controllers.impl;

import com.ruanmoraes.student_management_api.controllers.DisciplineControllerDocs;
import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.services.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController implements DisciplineControllerDocs {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DisciplineResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(disciplineService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplineService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> create(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplineService.create(disciplineRequestDTO));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> updateById(
            @PathVariable Long id,
            @Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplineService.updateById(id, disciplineRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        disciplineService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
