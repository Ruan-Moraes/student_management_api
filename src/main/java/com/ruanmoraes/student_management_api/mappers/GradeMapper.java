package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.models.GradeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "enrollment", ignore = true)
    GradeResponseDTO toDTO(GradeModel grade);

    @Mapping(target = "enrollment", ignore = true)
    GradeModel toModel(GradeResponseDTO gradeResponseDTO);
}
