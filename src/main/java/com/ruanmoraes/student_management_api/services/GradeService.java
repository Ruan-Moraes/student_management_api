package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageByDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.StudentGradesResponseDTO;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.mappers.EnrollmentMapper;
import com.ruanmoraes.student_management_api.mappers.GradeMapper;
import com.ruanmoraes.student_management_api.mappers.StudentMapper;
import com.ruanmoraes.student_management_api.models.Enrollment;
import com.ruanmoraes.student_management_api.models.Grade;
import com.ruanmoraes.student_management_api.models.Student;
import com.ruanmoraes.student_management_api.repositories.GradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;
    private final DisciplineService disciplinesService;

    public GradeService(GradeRepository gradeRepository, StudentService studentService, DisciplineService disciplinesService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.disciplinesService = disciplinesService;
    }

    public List<GradeWithStudentAndDisciplineResponseDTO> findAll() {
        return gradeRepository.findAll().stream()
                .map(grade -> {
                    Long id = grade.getId();
                    String studentName = grade.getEnrollment().getStudent().getName();
                    String disciplineName = grade.getEnrollment().getDiscipline().getName();
                    Double gradeValue = grade.getGradeValue();

                    return new
                            GradeWithStudentAndDisciplineResponseDTO(id, studentName, disciplineName, gradeValue);
                })
                .toList();
    }

    public StudentGradesResponseDTO findAllGradesByStudentId(Long studentId) {
        Student student = StudentMapper.INSTANCE.toModel(studentService.findById(studentId));

        String studentName = student.getName();
        Map<String, Double> grades = new HashMap<>();

        List<Grade> filtedGrades = gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(studentId)).toList();

        filtedGrades.forEach(grade -> {
            String disciplineName = grade.getEnrollment().getDiscipline().getName();
            Double gradeValue = grade.getGradeValue();

            grades.put(disciplineName, gradeValue);
        });

        return new StudentGradesResponseDTO(studentName, grades);
    }

    public AvarageResponseDTO calculateAverageAllGrade() {
        Long totalGrades = gradeRepository.count();

        return new AvarageResponseDTO(gradeRepository
                .findAll().stream()
                .map(Grade::getGradeValue)
                .reduce(0.0, Double::sum) / totalGrades);
    }

    public AvarageResponseDTO averageGradeByStudentId(Long studentId) {
        StudentGradesResponseDTO studentGrades = findAllGradesByStudentId(studentId);

        Double grades = studentGrades.getGrades().values().stream()
                .reduce(0.0, Double::sum) / studentGrades.getGrades().size();

        return new AvarageResponseDTO(grades);
    }

    public AvarageByDisciplineResponseDTO calculateAvarageAllGradeByDiscipline() {
        Map<String, Double> avarageByDiscipline = new HashMap<>();

        List<String> disciplines = disciplinesService.findAll().stream()
                .map(DisciplineResponseDTO::getName)
                .toList();

        disciplines.forEach(disciplineName -> {
            Integer totalGradesByDiscipline = gradeRepository.findAll().stream()
                    .filter(grade -> grade.getEnrollment().getDiscipline().getName().equals(disciplineName)).toList().size();

            Double averageByDiscipline = gradeRepository.findAll().stream()
                    .filter(grade -> grade.getEnrollment().getDiscipline().getName().equals(disciplineName))
                    .map(Grade::getGradeValue)
                    .reduce(0.0, Double::sum) / totalGradesByDiscipline;

            avarageByDiscipline.put(disciplineName, averageByDiscipline);
        });

        return new AvarageByDisciplineResponseDTO(avarageByDiscipline);
    }


    public List<GradeWithStudentAndDisciplineResponseDTO> findAboveAverageStudents() {
        Double average = calculateAverageAllGrade().getAverage();

        return gradeRepository.findAll().stream()
                .filter(grade -> grade.getGradeValue() > average)
                .map(grade -> {
                    Long studentId = grade.getEnrollment().getStudent().getId();
                    String studentName = grade.getEnrollment().getStudent().getName();
                    String disciplineName = grade.getEnrollment().getDiscipline().getName();
                    Double gradeValue = grade.getGradeValue();

                    return new GradeWithStudentAndDisciplineResponseDTO(studentId, studentName, disciplineName, gradeValue);
                })
                .toList();

    }

    public GradeResponseDTO create(EnrollmentResponseDTO enrollmentResponseDTO, GradeRequestDTO gradeRequestDTO) {
        gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(enrollmentResponseDTO.getStudentId()))
                .filter(grade -> grade.getEnrollment().getDiscipline().getId().equals(enrollmentResponseDTO.getDisciplineId()))
                .findAny().ifPresent(
                        (grade) -> {
                            throw new ResourceAlreadyCreatedException();
                        }
                );

        Double gradeValue = gradeRequestDTO.getGradeValue();
        Enrollment enrollment = EnrollmentMapper.INSTANCE.toModel(enrollmentResponseDTO);

        return GradeMapper.INSTANCE.toDTO(gradeRepository.save(new Grade(null, gradeValue, enrollment)));
    }
}
