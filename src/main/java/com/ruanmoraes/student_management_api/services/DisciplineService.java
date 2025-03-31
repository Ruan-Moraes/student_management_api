package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.NameConflictException;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.hateoas.DisciplineAssembler;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.models.DisciplineModel;
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
        DisciplineModel disciplineModel = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return disciplineAssembler.toModel(disciplineModel);
    }

    public DisciplineResponseDTO create(DisciplineRequestDTO disciplineRequestDTO) throws NameConflictException {
        DisciplineModel disciplineModel = DisciplineMapper.INSTANCE.toModel(disciplineRequestDTO);

        if (disciplineRepository.existsByName(disciplineModel.getName())) {
            throw new NameConflictException(disciplineModel.getName());
        }

        disciplineRepository.save(disciplineModel);

        return disciplineAssembler.toModel(disciplineModel);
    }

    public DisciplineResponseDTO updateById(Long id, DisciplineRequestDTO disciplineRequestDTO) throws NameConflictException, ResourceNotFoundException {
        DisciplineModel disciplineModel = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        disciplineModel.setName(disciplineRequestDTO.getName());

        if (disciplineRepository.existsByName(disciplineModel.getName())) {
            throw new NameConflictException(disciplineModel.getName());
        }

        disciplineRepository.save(disciplineModel);

        return disciplineAssembler.toModel(disciplineModel);
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        DisciplineModel disciplineModel = disciplineRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        disciplineRepository.deleteById(disciplineModel.getId());
    }
}
