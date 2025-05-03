package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.StudentController;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.StudentModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentAssembler extends RepresentationModelAssemblerSupport<StudentModel, StudentResponseDTO> {
    public StudentAssembler() {
        super(StudentController.class, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO toModel(StudentModel entity) {
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(entity);

        studentResponseDTO.add(linkTo(methodOn(StudentController.class).findAll()).withRel("FindAll").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).findById(null)).withSelfRel().withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).findByLowFrequency(null)).withRel("ListByLowFrequency").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).create(null)).withRel("Create").withType("POST"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withRel("Update").withType("PUT"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(null)).withRel("Delete").withType("DELETE"));

        return studentResponseDTO;
    }
}


