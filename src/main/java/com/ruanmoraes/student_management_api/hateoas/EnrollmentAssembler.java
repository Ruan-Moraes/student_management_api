package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.EnrollmentController;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.mappers.EnrollmentMapper;
import com.ruanmoraes.student_management_api.models.Enrollment;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnrollmentAssembler extends RepresentationModelAssemblerSupport<Enrollment, EnrollmentResponseDTO> {
    public EnrollmentAssembler() {
        super(EnrollmentController.class, EnrollmentResponseDTO.class);
    }

    @Override
    public EnrollmentResponseDTO toModel(Enrollment entity) {
        EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentMapper.INSTANCE.toDTO(entity);
        enrollmentResponseDTO.setStudentId(entity.getStudent().getId());
        enrollmentResponseDTO.setDisciplineId(entity.getDiscipline().getId());

        enrollmentResponseDTO.add(linkTo(methodOn(EnrollmentController.class).findById(enrollmentResponseDTO.getId())).withSelfRel().withType("GET"));
        enrollmentResponseDTO.add(linkTo(methodOn(EnrollmentController.class).findByStudentIdAndDisciplineId(null, null)).withRel("FindByStudentIdAndDisciplineId").withType("GET"));
        enrollmentResponseDTO.add(linkTo(methodOn(EnrollmentController.class).findAll()).withRel("FindAll").withType("GET"));
        enrollmentResponseDTO.add(linkTo(methodOn(EnrollmentController.class).create(null)).withRel("Create").withType("POST"));
        enrollmentResponseDTO.add(linkTo(methodOn(EnrollmentController.class).deleteById(enrollmentResponseDTO.getId())).withRel("Delete").withType("DELETE"));

        return enrollmentResponseDTO;
    }
}
