package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.impl.GradeController;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.mappers.GradeMapper;
import com.ruanmoraes.student_management_api.models.GradeModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GradeAssembler extends RepresentationModelAssemblerSupport<GradeModel, GradeResponseDTO> {
    public GradeAssembler() {
        super(GradeController.class, GradeResponseDTO.class);
    }

    @Override
    public GradeResponseDTO toModel(GradeModel entity) {
        GradeResponseDTO gradeResponseDTO = GradeMapper.INSTANCE.toDTO(entity);

        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAll()).withRel("FindAll").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAllGradesByStudentId(null)).withRel("FindAllGradesByStudentId").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAverageForEachStudent()).withRel("FindById").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAverageStudentById(null)).withRel("AverageGradeByStudentId").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).findAboveAverageStudents()).withRel("FindAboveAverageStudents").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).calculateAverageAllGrades()).withRel("CalculateAverageAllGrade").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).calculateAverageGradesByDiscipline()).withRel("CalculateAvarageAllGradeByDiscipline").withType("GET"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).create(null, null, null)).withRel("Create").withType("POST"));
        gradeResponseDTO.add(linkTo(methodOn(GradeController.class).update(null, null, null)).withRel("Update").withType("PUT"));

        return gradeResponseDTO;
    }
}
