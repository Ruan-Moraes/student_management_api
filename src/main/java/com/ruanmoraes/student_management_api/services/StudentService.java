package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.hateoas.StudentAssembler;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.StudentModel;
import com.ruanmoraes.student_management_api.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentAssembler studentAssembler;

    public StudentService(StudentRepository studentRepository, StudentAssembler studentAssembler) {
        this.studentRepository = studentRepository;
        this.studentAssembler = studentAssembler;
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(studentAssembler::toModel)
                .toList();
    }

    public StudentResponseDTO findById(Long id) throws ResourceNotFoundException {
        StudentModel student = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return studentAssembler.toModel(student);
    }

    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {
        StudentModel student = StudentMapper.INSTANCE.toModel(studentRequestDTO);

        studentRepository.save(student);

        return studentAssembler.toModel(student);
    }

    public StudentResponseDTO updateById(StudentRequestDTO studentRequestDTO) throws ResourceNotFoundException {
        StudentModel student = studentRepository.findById(studentRequestDTO.getId()).orElseThrow(ResourceNotFoundException::new);
        student.setName(studentRequestDTO.getName());
        student.setFrequency(studentRequestDTO.getFrequency());

        studentRepository.save(student);

        return studentAssembler.toModel(student);
    }

    public List<StudentResponseDTO> listByLowFrequency(Double frequency) {
        return studentRepository.findAll().stream().filter(student -> student.getFrequency() < frequency)
                .map(studentAssembler::toModel)
                .toList();
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        StudentModel student = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        studentRepository.deleteById(student.getId());
    }
}
