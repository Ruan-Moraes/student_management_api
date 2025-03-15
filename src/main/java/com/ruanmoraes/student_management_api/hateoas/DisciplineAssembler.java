package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.DisciplineController;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.models.Discipline;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DisciplineAssembler extends RepresentationModelAssemblerSupport<Discipline, DisciplineResponseDTO> {
    public DisciplineAssembler() {
        super(DisciplineController.class, DisciplineResponseDTO.class);
    }

    @Override
    public DisciplineResponseDTO toModel(Discipline entity) {
        DisciplineResponseDTO disciplineResponseDTO = DisciplineMapper.INSTANCE.toDTO(entity);

        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).findById(entity.getId())).withSelfRel().withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).findAll()).withRel("FindAll").withType("GET"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).create(null)).withRel("Create").withType("POST"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).updateById(null)).withRel("Update").withType("PUT"));
        disciplineResponseDTO.add(linkTo(methodOn(DisciplineController.class).deleteById(entity.getId())).withRel("Delete").withType("DELETE"));

        return disciplineResponseDTO;
    }
}
