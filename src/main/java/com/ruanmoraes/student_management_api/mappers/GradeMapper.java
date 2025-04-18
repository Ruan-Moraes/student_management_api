package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.models.GradeModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    GradeResponseDTO toDTO(GradeModel grade);

    GradeModel toModel(GradeResponseDTO gradeResponseDTO);
}
