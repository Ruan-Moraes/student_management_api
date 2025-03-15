package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.models.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisciplineMapper {
    DisciplineMapper INSTANCE = Mappers.getMapper(DisciplineMapper.class);

    Discipline toModel(DisciplineRequestDTO disciplineDTO);

    Discipline toModel(DisciplineResponseDTO disciplineDTO);

    DisciplineResponseDTO toDTO(Discipline discipline);
}
