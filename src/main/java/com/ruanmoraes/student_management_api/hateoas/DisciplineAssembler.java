package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.DisciplineController;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.models.DisciplineModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DisciplineAssembler extends RepresentationModelAssemblerSupport<DisciplineModel, DisciplineResponseDTO> {
    public DisciplineAssembler() {
        super(DisciplineController.class, DisciplineResponseDTO.class);
    }

    @Override
    public DisciplineResponseDTO toModel(DisciplineModel entity) {
        DisciplineResponseDTO dto = DisciplineMapper.INSTANCE.toDTO(entity);

        dto.add(linkTo(methodOn(DisciplineController.class).findAll()).withRel("FindAll").withType("GET"));
        dto.add(linkTo(methodOn(DisciplineController.class).findById(null)).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(DisciplineController.class).create(null)).withRel("Create").withType("POST"));
        dto.add(linkTo(methodOn(DisciplineController.class).updateById(null, null)).withRel("Update").withType("PUT"));
        dto.add(linkTo(methodOn(DisciplineController.class).deleteById(null)).withRel("Delete").withType("DELETE"));

        return dto;
    }
}
