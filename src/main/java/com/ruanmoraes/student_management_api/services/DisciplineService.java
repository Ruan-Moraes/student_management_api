package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.hateoas.DisciplineAssembler;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.models.Discipline;
import com.ruanmoraes.student_management_api.repositories.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    private final DisciplineAssembler disciplineAssembler;

    public DisciplineService(DisciplineRepository disciplineRepository, DisciplineAssembler disciplineAssembler) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineAssembler = disciplineAssembler;
    }

    public List<DisciplineResponseDTO> findAll() {
        return disciplineRepository.findAll().stream()
                .map(disciplineAssembler::toModel)
                .toList();
    }

    public DisciplineResponseDTO findById(Long id) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return disciplineAssembler.toModel(discipline);
    }

    public DisciplineResponseDTO create(DisciplineRequestDTO disciplineRequestDTO) {
        Discipline discipline = DisciplineMapper.INSTANCE.toModel(disciplineRequestDTO);

        disciplineRepository.save(discipline);

        return disciplineAssembler.toModel(discipline);
    }

    public DisciplineResponseDTO updateById(DisciplineRequestDTO disciplineRequestDTO) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(disciplineRequestDTO.getId()).orElseThrow(ResourceNotFoundException::new);
        discipline.setName(disciplineRequestDTO.getName());

        disciplineRepository.save(discipline);

        return disciplineAssembler.toModel(discipline);
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        disciplineRepository.deleteById(discipline.getId());
    }
}
