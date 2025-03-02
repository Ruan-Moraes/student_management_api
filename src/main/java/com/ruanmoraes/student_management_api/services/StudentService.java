package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.controllers.StudentController;
import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.Student;
import com.ruanmoraes.student_management_api.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(student);
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel().withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withRel("FindById").withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(student.getFrequency())).withRel("ListByLowFrequency").withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withRel("Create").withType("POST"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withRel("Update").withType("PUT"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(student.getId())).withRel("Delete").withType("DELETE"));

                    return studentResponseDTO;
                })
                .toList();
    }

    public StudentResponseDTO getStudentById(Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(student);
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withSelfRel().withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withRel("FindAll").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(student.getFrequency())).withRel("ListByLowFrequency").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withRel("Create").withType("POST"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withRel("Update").withType("PUT"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(student.getId())).withRel("Delete").withType("DELETE"));

        return studentResponseDTO;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = StudentMapper.INSTANCE.toModel(studentRequestDTO);

        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(student);
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withSelfRel().withType("POST"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withRel("FindAll").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withRel("FindById").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(student.getFrequency())).withRel("ListByLowFrequency").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withRel("Update").withType("PUT"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(student.getId())).withRel("Delete").withType("DELETE"));

        return StudentMapper.INSTANCE.toDTO(studentRepository.save(student));
    }

    public StudentResponseDTO updateById(Long id, StudentRequestDTO studentRequestDTO) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        student.setName(studentRequestDTO.getName());
        student.setFrequency(studentRequestDTO.getFrequency());

        studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(student);
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withSelfRel().withType("PUT"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withRel("FindAll").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withRel("FindById").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(student.getFrequency())).withRel("ListByLowFrequency").withType("GET"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withRel("Create").withType("POST"));
        studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(student.getId())).withRel("Delete").withType("DELETE"));

        return studentResponseDTO;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        studentRepository.delete(student);
    }

    public List<StudentResponseDTO> listStudentsByLowFrequency(Double frequency) {
        return studentRepository.findAll().stream().filter(student -> student.getFrequency() < frequency)
                .map(student -> {
                    StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(student);

                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).listStudentsByLowFrequency(frequency)).withSelfRel().withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel().withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withRel("FindById").withType("GET"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).createStudent(null)).withRel("Create").withType("POST"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).updateById(null, null)).withRel("Update").withType("PUT"));
                    studentResponseDTO.add(linkTo(methodOn(StudentController.class).deleteById(student.getId())).withRel("Delete").withType("DELETE"));

                    return studentResponseDTO;
                })
                .toList();
    }
}
