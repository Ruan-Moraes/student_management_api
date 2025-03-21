package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.models.DisciplineModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisciplineMapper {
    DisciplineMapper INSTANCE = Mappers.getMapper(DisciplineMapper.class);

    @Mapping(target = "enrollments", ignore = true)
    DisciplineModel toModel(DisciplineRequestDTO disciplineDTO);

    @Mapping(target = "enrollments", ignore = true)
    DisciplineModel toModel(DisciplineResponseDTO disciplineDTO);

    DisciplineResponseDTO toDTO(DisciplineModel discipline);
}
