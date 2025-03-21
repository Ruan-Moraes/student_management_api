package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageByDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.AvarageResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.StudentGradesResponseDTO;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.hateoas.GradeAssembler;
import com.ruanmoraes.student_management_api.hateoas.custom.AvarageAssembler;
import com.ruanmoraes.student_management_api.hateoas.custom.AvarageByDisciplineAssembler;
import com.ruanmoraes.student_management_api.hateoas.custom.GradeWithStudentAndDisciplineAssembler;
import com.ruanmoraes.student_management_api.hateoas.custom.StudentGradesAssembler;
import com.ruanmoraes.student_management_api.mappers.EnrollmentMapper;
import com.ruanmoraes.student_management_api.models.EnrollmentModel;
import com.ruanmoraes.student_management_api.models.GradeModel;
import com.ruanmoraes.student_management_api.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeAssembler gradeAssembler;

    private final AvarageAssembler avarageAssembler;
    private final AvarageByDisciplineAssembler avarageByDisciplineAssembler;
    private final GradeWithStudentAndDisciplineAssembler gradeWithStudentAndDisciplineAssembler;
    private final StudentGradesAssembler studentGradesAssembler;

    private final StudentService studentService;
    private final DisciplineService disciplinesService;

    public GradeService(GradeRepository gradeRepository,
                        GradeAssembler gradeAssembler,
                        AvarageAssembler avarageAssembler,
                        AvarageByDisciplineAssembler avarageByDisciplineAssembler,
                        GradeWithStudentAndDisciplineAssembler gradeWithStudentAndDisciplineAssembler,
                        StudentGradesAssembler studentGradesAssembler,
                        StudentService studentService, DisciplineService disciplinesService) {
        this.gradeRepository = gradeRepository;
        this.gradeAssembler = gradeAssembler;

        this.avarageAssembler = avarageAssembler;
        this.avarageByDisciplineAssembler = avarageByDisciplineAssembler;
        this.gradeWithStudentAndDisciplineAssembler = gradeWithStudentAndDisciplineAssembler;
        this.studentGradesAssembler = studentGradesAssembler;

        this.studentService = studentService;
        this.disciplinesService = disciplinesService;
    }

    public List<GradeWithStudentAndDisciplineResponseDTO> findAll() {
        return gradeRepository.findAll().stream()
                .map(grade -> {
                    Long studentId = grade.getEnrollment().getStudent().getId();
                    String studentName = grade.getEnrollment().getStudent().getName();
                    String disciplineName = grade.getEnrollment().getDiscipline().getName();
                    Double gradeValue = grade.getGradeValue();

                    return new GradeWithStudentAndDisciplineResponseDTO(studentId, studentName, disciplineName, gradeValue);
                })
                .map(gradeWithStudentAndDisciplineAssembler::toModel).toList();
    }

    public StudentGradesResponseDTO findAllGradesByStudentId(Long studentId) {
        StudentResponseDTO studentResponseDTO = studentService.findById(studentId);

        String studentName = studentResponseDTO.getName();
        Map<String, Double> grades = new HashMap<>();

        List<GradeModel> filtedGrades = gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(studentId))
                .toList();


        filtedGrades.forEach(grade -> {
            String disciplineName = grade.getEnrollment().getDiscipline().getName();
            Double gradeValue = grade.getGradeValue();

            grades.put(disciplineName, gradeValue);
        });

        StudentGradesResponseDTO studentGradesResponseDTO = new StudentGradesResponseDTO(studentId, studentName, grades);

        return studentGradesAssembler.toModel(studentGradesResponseDTO);
    }

    public AvarageResponseDTO calculateAverageAllGrade() {
        Long totalGrades = gradeRepository.count();

        AvarageResponseDTO avarageResponseDTO = new AvarageResponseDTO(gradeRepository
                .findAll().stream()
                .map(GradeModel::getGradeValue)
                .reduce(0.0, Double::sum) / totalGrades);

        return avarageAssembler.toModel(avarageResponseDTO);
    }

    public AvarageResponseDTO averageGradeByStudentId(Long studentId) {
        StudentGradesResponseDTO studentGrades = findAllGradesByStudentId(studentId);

        double avarage = studentGrades
                .getGrades()
                .values()
                .stream()
                .reduce(0.0, Double::sum) / studentGrades.getGrades().size();

        if (Double.isNaN(avarage)) {
            avarage = 0.0;
        }

        return avarageAssembler.toModel(new AvarageResponseDTO(avarage));
    }

    public AvarageByDisciplineResponseDTO calculateAvarageAllGradeByDiscipline() {
        Map<String, Double> avarageByDiscipline = new HashMap<>();

        List<String> disciplines = disciplinesService.findAll().stream()
                .map(DisciplineResponseDTO::getName)
                .toList();

        System.out.println(disciplines);

        disciplines.forEach(disciplineName -> {
            Integer totalGradesByDiscipline = gradeRepository.findAll().stream()
                    .filter(grade -> grade.getEnrollment().getDiscipline().getName().equals(disciplineName)).toList().size();

            if (totalGradesByDiscipline == 0) {
                avarageByDiscipline.put(disciplineName, 0.0);

                return;
            }

            Double averageByDiscipline = gradeRepository.findAll().stream()
                    .filter(grade -> grade.getEnrollment().getDiscipline().getName().equals(disciplineName))
                    .map(GradeModel::getGradeValue)
                    .reduce(0.0, Double::sum) / totalGradesByDiscipline;

            avarageByDiscipline.put(disciplineName, averageByDiscipline);
        });

        return avarageByDisciplineAssembler.toModel(new AvarageByDisciplineResponseDTO(avarageByDiscipline));
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
                .map(gradeWithStudentAndDisciplineAssembler::toModel)
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
        EnrollmentModel enrollment = EnrollmentMapper.INSTANCE.toModel(enrollmentResponseDTO);

        return gradeAssembler.toModel(gradeRepository.save(new GradeModel(null, gradeValue, enrollment)));
    }
}
