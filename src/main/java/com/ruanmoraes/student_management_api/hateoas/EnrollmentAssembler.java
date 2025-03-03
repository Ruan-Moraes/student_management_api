package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.EnrollmentController;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.mappers.EnrollmentMapper;
import com.ruanmoraes.student_management_api.models.Enrollment;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class EnrollmentService extends RepresentationModelAssemblerSupport<Enrollment, EnrollmentResponseDTO> {
    public EnrollmentService() {
        super(EnrollmentController.class, EnrollmentResponseDTO.class);
    }

    @Override
    public EnrollmentResponseDTO toModel(Enrollment entity) {
        EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentMapper.INSTANCE.toDTO(entity);

        return null;
    }
}
