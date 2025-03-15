package com.ruanmoraes.student_management_api.services;

import com.ruanmoraes.student_management_api.dtos.custom.response.GradeWithStudentAndDisciplineResponseDTO;
import com.ruanmoraes.student_management_api.dtos.custom.response.StudentGradesResponseDTO;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
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

    public GradeService(GradeRepository gradeRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
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

        log.warn(String.valueOf(student));

        String studentName = student.getName();
        Map<String, Double> grades = new HashMap<>();

        List<Grade> filtedGrades = gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(studentId)).toList();

        filtedGrades.forEach(grade -> {
            String disciplineName = grade.getEnrollment().getDiscipline().getName();
            Double gradeValue = grade.getGradeValue();

            grades.put(disciplineName, gradeValue);
        });

        log.warn(String.valueOf(new StudentGradesResponseDTO(studentName, grades)));

        return new StudentGradesResponseDTO(studentName, grades);
    }

    //    public Double calcularMediaNotasTodosAlunos() {
//        return notaRepository.findAll().stream()
//                .map(Nota::getValorNota)
//                .reduce(0.0, Double::sum) / notaRepository.count();
//    }
//
//    public Double calcularMediaNotasAlunoPorId(Long alunoId) {
//        int quantidadeDisciplinas = notaRepository.findAll().stream()
//                .filter(nota -> nota.getEnrollment().getAluno().getId().equals(alunoId))
//                .map(nota -> nota.getEnrollment().getDisciplina().getId())
//                .distinct()
//                .toList()
//                .size();
//
//
//        return notaRepository.findAll().stream()
//                .filter(nota -> nota.getEnrollment().getAluno().getId().equals(alunoId))
//                .map(Nota::getValorNota)
//                .reduce(0.0, Double::sum) / quantidadeDisciplinas;
//    }
//
//    public Double calcularMediaNotasDisciplinaPorId(Long disciplinaId) {
//        int quantidadeAlunos = notaRepository.findAll().stream()
//                .filter(nota -> nota.getEnrollment().getDisciplina().getId().equals(disciplinaId))
//                .map(nota -> nota.getEnrollment().getAluno().getId())
//                .distinct()
//                .toList()
//                .size();
//
//        return notaRepository.findAll().stream()
//                .filter(nota -> nota.getEnrollment().getDisciplina().getId().equals(disciplinaId))
//                .map(Nota::getValorNota)
//                .reduce(0.0, Double::sum) / quantidadeAlunos;
//    }
//
//    public List<DisciplinaDTO>
//    calcularMediaTodosAlunosDisciplina() {
//        int quantidadeDisciplinas = notaRepository.findAll().stream()
//                .map(nota -> nota.getEnrollment().getDisciplina().getId())
//                .distinct()
//                .toList()
//                .size();
//
//        return notaRepository.findAll().stream()
//                .map(nota -> nota.getEnrollment().getDisciplina().getId())
//                .distinct()
//                .map(disciplinaId -> {
//                    String nomeDisciplina = notaRepository.findAll().stream()
//                            .filter(nota -> nota.getEnrollment().getDisciplina().getId().equals(disciplinaId))
//                            .findFirst()
//                            .map(nota -> nota.getEnrollment().getDisciplina().getNome())
//                            .orElse(null);
//
//                    Double mediaDisciplina = calcularMediaNotasDisciplinaPorId(disciplinaId);
//
//                    return new DisciplinaDTO(disciplinaId, nomeDisciplina, mediaDisciplina);
//                })
//                .toList();
//    }
//
//    public List<StudentGradesView> buscarAlunosAcimaMediaTurma() {
//        Double mediaTurma = calcularMediaNotasTodosAlunos();
//
//        return notaRepository.findAll().stream()
//                .map(nota -> nota.getEnrollment().getAluno().getId())
//                .distinct()
//                .map(alunoId -> {
//                    String nomeAluno = notaRepository.findAll().stream()
//                            .filter(nota -> nota.getEnrollment().getAluno().getId().equals(alunoId))
//                            .findFirst()
//                            .map(nota -> nota.getEnrollment().getAluno().getNome())
//                            .orElse(null);
//
//                    Double mediaAluno = calcularMediaNotasAlunoPorId(alunoId);
//
//                    return new StudentGradesView(nomeAluno, mediaAluno);
//                })
//                .filter(studentGradesView -> studentGradesView.getMedia() > mediaTurma)
//                .toList();
//    }
//
    public GradeResponseDTO create(Enrollment enrollment, GradeRequestDTO gradeRequestDTO) {
        gradeRepository.findAll().stream()
                .filter(grade -> grade.getEnrollment().getStudent().getId().equals(enrollment.getStudent().getId()))
                .filter(grade -> grade.getEnrollment().getDiscipline().getId().equals(enrollment.getDiscipline().getId()))
                .findAny().ifPresent(
                        (grade) -> {
                            throw new ResourceAlreadyCreatedException();
                        }
                );

        double newGrade = gradeRequestDTO.getGradeValue();

        Grade grade = new Grade(null,
                newGrade,
                enrollment
        );

        return GradeMapper.INSTANCE.toDTO(gradeRepository.save(grade));
    }
}
