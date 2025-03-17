package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.models.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    @Mapping(target = "grade", ignore = true)
    EnrollmentResponseDTO toDTO(Enrollment enrollment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "discipline", ignore = true)
    @Mapping(target = "grade", ignore = true)
    Enrollment toModel(EnrollmentResponseDTO enrollmentResponseDTO);
}
