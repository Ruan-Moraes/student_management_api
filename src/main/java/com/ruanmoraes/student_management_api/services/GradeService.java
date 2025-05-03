package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.custom.response.*;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.hateoas.GradeAssembler;
import com.ruanmoraes.student_management_api.hateoas.custom.*;
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

    private final AverageOfAllGradesAssembler averageOfAllGradesAssembler;
    private final AverageStudentGradesAssembler averageStudentGradesAssembler;
    private final AverageByDisciplineAssembler averageByDisciplineAssembler;
    private final GradeWithStudentAndDisciplineAssembler gradeWithStudentAndDisciplineAssembler;
    private final StudentGradesAssembler studentGradesAssembler;

    private final StudentService studentService;
    private final DisciplineService disciplinesService;
    private final EnrollmentService enrollmentService;

    public GradeService(GradeRepository gradeRepository,
                        GradeAssembler gradeAssembler,
                        AverageOfAllGradesAssembler averageOfAllGradesAssembler,
                        AverageStudentGradesAssembler averageStudentGradesAssembler,
                        AverageByDisciplineAssembler averageByDisciplineAssembler,
                        GradeWithStudentAndDisciplineAssembler gradeWithStudentAndDisciplineAssembler,
                        StudentGradesAssembler studentGradesAssembler,
                        StudentService studentService, DisciplineService disciplinesService,
                        EnrollmentService enrollmentService
    ) {
        this.gradeRepository = gradeRepository;
        this.gradeAssembler = gradeAssembler;

        this.averageOfAllGradesAssembler = averageOfAllGradesAssembler;
        this.averageStudentGradesAssembler = averageStudentGradesAssembler;
        this.averageByDisciplineAssembler = averageByDisciplineAssembler;
        this.gradeWithStudentAndDisciplineAssembler = gradeWithStudentAndDisciplineAssembler;
        this.studentGradesAssembler = studentGradesAssembler;

        this.studentService = studentService;
        this.disciplinesService = disciplinesService;
        this.enrollmentService = enrollmentService;
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

    public AverageStudentGradesResponseDTO findAverageStudentById(Long studentId) {
        StudentGradesResponseDTO studentGrades = findAllGradesByStudentId(studentId);

        String name = studentGrades.getStudentName();

        double avarage = studentGrades
                .getGrades()
                .values()
                .stream()
                .reduce(0.0, Double::sum) / studentGrades.getGrades().size();

        if (Double.isNaN(avarage)) {
            avarage = 0.0;
        }

        return averageStudentGradesAssembler.toModel(new AverageStudentGradesResponseDTO(name, avarage));
    }

    public List<AverageStudentGradesResponseDTO> findAverageForEachStudent() {
        return studentService.findAll().stream()
                .map(student -> {
                    Long studentId = student.getId();
                    String studentName = student.getName();

                    double avarage = findAllGradesByStudentId(studentId).getGrades().values().stream()
                            .reduce(0.0, Double::sum) / findAllGradesByStudentId(studentId).getGrades().size();

                    if (Double.isNaN(avarage)) {
                        avarage = 0.0;
                    }

                    return new AverageStudentGradesResponseDTO(studentName, avarage);
                })
                .map(averageStudentGradesAssembler::toModel)
                .toList();
    }

    public List<AverageStudentGradesResponseDTO> findAboveAverageStudents() {
        Double average = calculateAverageAllGrades().getAverage();

        return studentService.findAll().stream()
                .map(student -> {
                    Long studentId = student.getId();
                    String studentName = student.getName();

                    double avarage = findAllGradesByStudentId(studentId).getGrades().values().stream()
                            .reduce(0.0, Double::sum) / findAllGradesByStudentId(studentId).getGrades().size();

                    if (Double.isNaN(avarage)) {
                        avarage = 0.0;
                    }

                    return new AverageStudentGradesResponseDTO(studentName, avarage);
                })
                .filter(averageStudentGradesResponseDTO -> averageStudentGradesResponseDTO.getAverage() > average)
                .map(averageStudentGradesAssembler::toModel)
                .toList();
    }

    public AverageOfAllGradesResponseDTO calculateAverageAllGrades() {
        Long totalGrades = gradeRepository.count();

        Double average = gradeRepository
                .findAll().stream()
                .map(GradeModel::getGradeValue)
                .reduce(0.0, Double::sum) / totalGrades;

        if (Double.isNaN(average)) {
            average = 0.0;
        }

        return averageOfAllGradesAssembler.toModel(new AverageOfAllGradesResponseDTO(average));
    }

    public AverageByDisciplineResponseDTO calculateAverageGradesByDiscipline() {
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

        return averageByDisciplineAssembler.toModel(new AverageByDisciplineResponseDTO(avarageByDiscipline));
    }

    public GradeResponseDTO create(EnrollmentResponseDTO enrollmentResponseDTO, GradeRequestDTO gradeRequestDTO) throws ResourceAlreadyCreatedException {
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

    public GradeResponseDTO update(EnrollmentResponseDTO enrollmentResponseDTO, GradeRequestDTO gradeRequestDTO) {
        GradeModel gradeModel = gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(enrollmentResponseDTO.getStudentId()))
                .filter(grade -> grade.getEnrollment().getDiscipline().getId().equals(enrollmentResponseDTO.getDisciplineId()))
                .findAny()
                .orElseThrow(ResourceAlreadyCreatedException::new);

        gradeModel.setGradeValue(gradeRequestDTO.getGradeValue());

        return gradeAssembler.toModel(gradeRepository.save(gradeModel));
    }
}
