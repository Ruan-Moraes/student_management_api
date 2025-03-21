package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.request.EnrollmentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.hateoas.EnrollmentAssembler;
import com.ruanmoraes.student_management_api.mappers.DisciplineMapper;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.DisciplineModel;
import com.ruanmoraes.student_management_api.models.EnrollmentModel;
import com.ruanmoraes.student_management_api.models.StudentModel;
import com.ruanmoraes.student_management_api.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final DisciplineService disciplineService;
    private final EnrollmentAssembler enrollmentAssembler;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService, DisciplineService disciplineService, EnrollmentAssembler enrollmentAssembler) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.disciplineService = disciplineService;
        this.enrollmentAssembler = enrollmentAssembler;
    }

    public List<EnrollmentResponseDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentAssembler::toModel)
                .toList();
    }

    public EnrollmentResponseDTO findById(Long id) throws ResourceNotFoundException {
        EnrollmentModel enrollment = enrollmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return enrollmentAssembler.toModel(enrollment);
    }

    public EnrollmentResponseDTO findByStudentIdAndDisciplineId(Long studentId, Long disciplineId) throws ResourceNotFoundException {
        studentService.findById(studentId);
        disciplineService.findById(disciplineId);

        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .filter(e -> e.getDiscipline().getId().equals(disciplineId))
                .findFirst()
                .map(enrollmentAssembler::toModel)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public EnrollmentResponseDTO create(EnrollmentRequestDTO enrollmentRequestDTO) throws ResourceNotFoundException, ResourceAlreadyCreatedException {
        StudentModel student = StudentMapper.INSTANCE.toModel(studentService.findById(enrollmentRequestDTO.getStudentId()));
        DisciplineModel discipline = DisciplineMapper.INSTANCE.toModel(disciplineService.findById(enrollmentRequestDTO.getDisciplineId()));

        if (enrollmentRepository.findAll().stream()
                .anyMatch(e ->
                        e.getStudent().getId().equals(student.getId()) && e.getDiscipline().getId().equals(discipline.getId())
                )) {
            throw new ResourceAlreadyCreatedException();
        }

        EnrollmentModel enrollmentModel = new EnrollmentModel(
                null,
                student,
                discipline,
                null
        );

        enrollmentRepository.save(enrollmentModel);

        return enrollmentAssembler.toModel(enrollmentModel);
    }

    public void deleteById(Long id) {
        enrollmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        enrollmentRepository.deleteById(id);
    }
}
