package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.GradeController;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.mappers.GradeMapper;
import com.ruanmoraes.student_management_api.models.Grade;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GradeAssembler extends RepresentationModelAssemblerSupport<Grade, GradeResponseDTO> {
    public GradeAssembler() {
        super(GradeController.class, GradeResponseDTO.class);
    }

    @Override
    public GradeResponseDTO toModel(Grade entity) {
        GradeResponseDTO gradeResponseDTO = GradeMapper.INSTANCE.toDTO(entity);

        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAll()).withRel("FindAll").withType("GET"));

        return gradeResponseDTO;
    }
}
