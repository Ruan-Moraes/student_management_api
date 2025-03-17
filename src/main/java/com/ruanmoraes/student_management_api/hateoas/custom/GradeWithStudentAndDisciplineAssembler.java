package com.ruanmoraes.student_management_api.hateoas.custom;

import com.ruanmoraes.student_management_api.controllers.GradeController;
import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GradeWithStudentAndDisciplineAssembler extends RepresentationModelAssemblerSupport<GradeWithStudentAndDisciplineResponseDTO, GradeWithStudentAndDisciplineResponseDTO> {
    public GradeWithStudentAndDisciplineAssembler() {
        super(GradeController.class, GradeWithStudentAndDisciplineResponseDTO.class);
    }

    @Override
    public GradeWithStudentAndDisciplineResponseDTO toModel(GradeWithStudentAndDisciplineResponseDTO dto) {
        dto.add(linkTo(methodOn(GradeController.class).findAll()).withRel("FindAll").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAllGradesByStudentId(null)).withRel("FindAllGradesByStudentId").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAboveAverageStudents()).withRel("FindAboveAverageStudents").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).calculateAverageAllGrade()).withRel("CalculateAverageAllGrade").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).calculateAvarageAllGradeByDiscipline()).withRel("CalculateAvarageAllGradeByDiscipline").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).averageGradeByStudentId(null)).withRel("AverageGradeByStudentId").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).create(null, null, null)).withRel("Create").withType("POST"));

        return dto;
    }
}
