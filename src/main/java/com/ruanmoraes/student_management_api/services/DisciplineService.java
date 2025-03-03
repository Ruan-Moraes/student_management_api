package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.controllers.DisciplineController;
import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.models.Discipline;
import com.ruanmoraes.student_management_api.repositories.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public DisciplineResponseDTO createDiscipline(DisciplineRequestDTO disciplineRequestDTO) {
        Discipline discipline = DisciplineMapper.INSTANCE.toModel(disciplineRequestDTO);

        disciplineRepository.save(discipline);

        DisciplineResponseDTO disciplineResponseDTO = DisciplineMapper.INSTANCE.toDTO(discipline);
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).createDiscipline(null)).withSelfRel().withType("POST"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withRel("FindAll").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getById(discipline.getId())).withRel("FindById").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).updateById(discipline.getId(), null)).withRel("Update").withType("PUT"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).deleteById(discipline.getId())).withRel("Delete").withType("DELETE"));

        return disciplineResponseDTO;
    }

    public List<DisciplineResponseDTO> getAllDisciplines() {
        return disciplineRepository.findAll().stream()
                .map(discipline -> {
                    DisciplineResponseDTO disciplineResponseDTO = DisciplineMapper.INSTANCE.toDTO(discipline);
                    disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withSelfRel().withType("GET"));
                    disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getById(discipline.getId())).withRel("FindById").withType("GET"));
                    disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).createDiscipline(null)).withRel("Create").withType("POST"));
                    disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).updateById(discipline.getId(), null)).withRel("Update").withType("PUT"));
                    disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).deleteById(discipline.getId())).withRel("Delete").withType("DELETE"));

                    return disciplineResponseDTO;
                })
                .toList();
    }

    public DisciplineResponseDTO getById(Long id) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        DisciplineResponseDTO disciplineResponseDTO = DisciplineMapper.INSTANCE.toDTO(discipline);
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getById(id)).withSelfRel().withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withRel("FindAll").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).createDiscipline(null)).withRel("Create").withType("POST"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).updateById(id, null)).withRel("Update").withType("PUT"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).deleteById(id)).withRel("Delete").withType("DELETE"));

        return disciplineResponseDTO;
    }

    public DisciplineResponseDTO updateById(Long id, DisciplineRequestDTO disciplineRequestDTO) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        discipline.setName(disciplineRequestDTO.getName());

        disciplineRepository.save(discipline);

        DisciplineResponseDTO disciplineResponseDTO = DisciplineMapper.INSTANCE.toDTO(discipline);
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).updateById(id, disciplineRequestDTO)).withSelfRel().withType("PUT"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withRel("FindAll").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).getById(id)).withRel("FindById").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).createDiscipline(null)).withRel("Create").withType("POST"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).deleteById(id)).withRel("Delete").withType("DELETE"));

        return disciplineResponseDTO;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        disciplineRepository.delete(discipline);
    }
}
