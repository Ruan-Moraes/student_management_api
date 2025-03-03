package com.ruanmoraes.student_management_api.controllers;

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
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllDisciplines() {
        List<DisciplineResponseDTO> disciplines = disciplineService.getAllDisciplines();

        return ResponseEntity.status(200).body(disciplines);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DisciplineResponseDTO discipline = disciplineService.getById(id);

        return ResponseEntity.status(200).body(discipline);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createDiscipline(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        DisciplineResponseDTO discipline = disciplineService.createDiscipline(disciplineRequestDTO);

        return ResponseEntity.status(201).body(discipline);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DisciplineResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO) {
        DisciplineResponseDTO discipline = disciplineService.updateById(id, disciplineRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(discipline);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        disciplineService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
