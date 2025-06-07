package com.ruanmoraes.student_management_api.hateoas.custom;

import com.ruanmoraes.student_management_api.controllers.impl.GradeController;
import com.ruanmoraes.student_management_api.dtos.custom.response.AverageOfAllGradesResponseDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AverageOfAllGradesAssembler extends RepresentationModelAssemblerSupport<AverageOfAllGradesResponseDTO, AverageOfAllGradesResponseDTO> {
    public AverageOfAllGradesAssembler() {
        super(GradeController.class, AverageOfAllGradesResponseDTO.class);
    }

    @Override
    public AverageOfAllGradesResponseDTO toModel(AverageOfAllGradesResponseDTO dto) {
        dto.add(linkTo(methodOn(GradeController.class).findAll()).withRel("FindAll").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAllGradesByStudentId(null)).withRel("FindAllGradesByStudentId").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAverageForEachStudent()).withRel("FindById").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAverageStudentById(null)).withRel("AverageGradeByStudentId").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).findAboveAverageStudents()).withRel("FindAboveAverageStudents").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).calculateAverageAllGrades()).withRel("CalculateAverageAllGrade").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).calculateAverageGradesByDiscipline()).withRel("CalculateAvarageAllGradeByDiscipline").withType("GET"));
        dto.add(linkTo(methodOn(GradeController.class).create(null, null, null)).withRel("Create").withType("POST"));
        dto.add(linkTo(methodOn(GradeController.class).update(null, null, null)).withRel("Update").withType("PUT"));

        return dto;
    }
}
