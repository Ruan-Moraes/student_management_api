package com.ruanmoraes.student_management_api.service;

import com.ruanmoraes.student_management_api.model.Aluno;
import com.ruanmoraes.student_management_api.model.Disciplina;
import com.ruanmoraes.student_management_api.model.Matricula;
import com.ruanmoraes.student_management_api.model.Nota;
import com.ruanmoraes.student_management_api.repository.AlunoRepository;
import com.ruanmoraes.student_management_api.repository.DisciplinaRepository;
import com.ruanmoraes.student_management_api.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public Matricula criarMatricula(Matricula matricula) {
        Aluno aluno = alunoRepository.findById(matricula.getAluno().getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(matricula.getDisciplina().getId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        matriculaRepository.findAll().stream()
                .filter(m -> m.getAluno().getId().equals(aluno.getId()))
                .filter(m -> m.getDisciplina().getId().equals(disciplina.getId()))
                .findFirst()
                .ifPresent(m -> {
                    throw new RuntimeException("Matrícula já existente");
                });

        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        matricula.setNota(new Nota(
                null,
                0.0,
                matricula
        ));

        return matriculaRepository.save(matricula);
    }

    public boolean removerMatricula(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
