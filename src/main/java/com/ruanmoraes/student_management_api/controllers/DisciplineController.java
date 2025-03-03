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

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<DisciplineResponseDTO>> getAllDisciplines() {
        List<DisciplineResponseDTO> disciplines = disciplineService.getAllDisciplines();

        return ResponseEntity.status(200).body(disciplines);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> getById(@PathVariable Long id) {
        DisciplineResponseDTO discipline = disciplineService.getById(id);

        return ResponseEntity.status(200).body(discipline);
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> createDiscipline(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        DisciplineResponseDTO discipline = disciplineService.createDiscipline(disciplineRequestDTO);

        return ResponseEntity.status(201).body(discipline);
    }

    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> updateById(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        DisciplineResponseDTO discipline = disciplineService.updateById(disciplineRequestDTO);

        return ResponseEntity.status(200).body(discipline);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        disciplineService.deleteById(id);

        return ResponseEntity.status(204).build();
    }
}
