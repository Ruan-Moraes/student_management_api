package com.ruanmoraes.student_management_api.hateoas;

import com.ruanmoraes.student_management_api.controllers.StudentController;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.Student;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentAssembler extends RepresentationModelAssemblerSupport<Student, StudentResponseDTO> {
    public StudentAssembler() {
        super(StudentController.class, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO toModel(Student entity) {
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(entity);

        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(studentResponseDTO.getId())).withSelfRel().withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withRel("FindAll").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(studentResponseDTO.getFrequency())).withRel("ListByLowFrequency").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withRel("Create").withType("POST"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null)).withRel("Update").withType("PUT"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(studentResponseDTO.getId())).withRel("Delete").withType("DELETE"));

        return studentResponseDTO;
    }
}


