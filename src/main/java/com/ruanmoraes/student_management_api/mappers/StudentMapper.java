package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.models.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "enrollments", ignore = true)
    StudentModel toModel(StudentRequestDTO studentRequestDTO);

    @Mapping(target = "enrollments", ignore = true)
    StudentModel toModel(StudentResponseDTO studentResponseDTO);

    StudentResponseDTO toDTO(StudentModel student);
}
