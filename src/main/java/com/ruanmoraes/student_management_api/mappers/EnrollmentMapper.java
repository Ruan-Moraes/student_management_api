package com.ruanmoraes.student_management_api.mappers;

import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.models.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentResponseDTO toDTO(Enrollment enrollment);

    Enrollment toModel(EnrollmentResponseDTO enrollmentResponseDTO);
}
