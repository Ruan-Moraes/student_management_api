package com.ruanmoraes.student_management_api.service;

import com.ruanmoraes.student_management_api.model.Aluno;
import com.ruanmoraes.student_management_api.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> buscarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno atualizarAlunoPorId(Long id, Aluno aluno) {
        Aluno alunoAtualizado = alunoRepository.findById(id).orElse(null);

        if (alunoAtualizado != null) {
            if (aluno.getPercentualFrequencia() < 0.0) {
                aluno.setPercentualFrequencia(0.0);
            }

            if (aluno.getPercentualFrequencia() > 100.0) {
                aluno.setPercentualFrequencia(100.0);
            }

            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setPercentualFrequencia(aluno.getPercentualFrequencia());

            return alunoRepository.save(alunoAtualizado);
        }

        return null;
    }

    public boolean deletarAlunoPorId(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public List<Aluno> listarAlunosComFrequenciaBaixa(Double frequencia) {
        return alunoRepository.findAll().stream()
                .filter(aluno -> aluno.getPercentualFrequencia() < frequencia)
                .toList();
    }
}
