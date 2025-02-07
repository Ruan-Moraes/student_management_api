package com.ruanmoraes.student_management_api.service;

import com.ruanmoraes.student_management_api.dto.AlunoMediaDTO;
import com.ruanmoraes.student_management_api.dto.DisciplinaDTO;
import com.ruanmoraes.student_management_api.dto.NotaDTO;
import com.ruanmoraes.student_management_api.model.Nota;
import com.ruanmoraes.student_management_api.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<NotaDTO> buscarTodasNotasAlunos() {
        return notaRepository.findAll().stream()
                .map(nota -> {
                    Long id = nota.getId();
                    Long disciplinaId = nota.getMatricula().getDisciplina().getId();
                    String nomeAluno = nota.getMatricula().getAluno().getNome();
                    String disciplina = nota.getMatricula().getDisciplina().getNome();
                    Double valorNota = nota.getValorNota();

                    return new NotaDTO(id, disciplinaId, nomeAluno, disciplina, valorNota);
                })
                .toList();
    }

    public List<NotaDTO> buscarTodasNotasAlunoPorId(Long alunoId) {
        return notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getAluno().getId().equals(alunoId))
                .map(nota -> {
                    Long id = nota.getId();
                    Long disciplinaId = nota.getMatricula().getDisciplina().getId();
                    String nomeAluno = nota.getMatricula().getAluno().getNome();
                    String disciplina = nota.getMatricula().getDisciplina().getNome();
                    Double valorNota = nota.getValorNota();

                    return new NotaDTO(id, disciplinaId, nomeAluno, disciplina, valorNota);
                })
                .toList();
    }

    public Double calcularMediaNotasTodosAlunos() {
        return notaRepository.findAll().stream()
                .map(Nota::getValorNota)
                .reduce(0.0, Double::sum) / notaRepository.count();
    }

    public Double calcularMediaNotasAlunoPorId(Long alunoId) {
        int quantidadeDisciplinas = notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getAluno().getId().equals(alunoId))
                .map(nota -> nota.getMatricula().getDisciplina().getId())
                .distinct()
                .toList()
                .size();


        return notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getAluno().getId().equals(alunoId))
                .map(Nota::getValorNota)
                .reduce(0.0, Double::sum) / quantidadeDisciplinas;
    }

    public Double calcularMediaNotasDisciplinaPorId(Long disciplinaId) {
        int quantidadeAlunos = notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getDisciplina().getId().equals(disciplinaId))
                .map(nota -> nota.getMatricula().getAluno().getId())
                .distinct()
                .toList()
                .size();

        return notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getDisciplina().getId().equals(disciplinaId))
                .map(Nota::getValorNota)
                .reduce(0.0, Double::sum) / quantidadeAlunos;
    }

    public List<DisciplinaDTO>
    calcularMediaTodosAlunosDisciplina() {
        int quantidadeDisciplinas = notaRepository.findAll().stream()
                .map(nota -> nota.getMatricula().getDisciplina().getId())
                .distinct()
                .toList()
                .size();

        return notaRepository.findAll().stream()
                .map(nota -> nota.getMatricula().getDisciplina().getId())
                .distinct()
                .map(disciplinaId -> {
                    String nomeDisciplina = notaRepository.findAll().stream()
                            .filter(nota -> nota.getMatricula().getDisciplina().getId().equals(disciplinaId))
                            .findFirst()
                            .map(nota -> nota.getMatricula().getDisciplina().getNome())
                            .orElse(null);

                    Double mediaDisciplina = calcularMediaNotasDisciplinaPorId(disciplinaId);

                    return new DisciplinaDTO(disciplinaId, nomeDisciplina, mediaDisciplina);
                })
                .toList();
    }

    public List<AlunoMediaDTO> buscarAlunosAcimaMediaTurma() {
        Double mediaTurma = calcularMediaNotasTodosAlunos();

        return notaRepository.findAll().stream()
                .map(nota -> nota.getMatricula().getAluno().getId())
                .distinct()
                .map(alunoId -> {
                    String nomeAluno = notaRepository.findAll().stream()
                            .filter(nota -> nota.getMatricula().getAluno().getId().equals(alunoId))
                            .findFirst()
                            .map(nota -> nota.getMatricula().getAluno().getNome())
                            .orElse(null);

                    Double mediaAluno = calcularMediaNotasAlunoPorId(alunoId);

                    return new AlunoMediaDTO(nomeAluno, mediaAluno);
                })
                .filter(alunoMediaDTO -> alunoMediaDTO.getMedia() > mediaTurma)
                .toList();
    }

    public NotaDTO atualizarNotaPorAlunoIdEDisciplinaId(Long alunoId, Long disciplinaId, NotaDTO notaDTO) {
        Nota notaAtualizada = notaRepository.findAll().stream()
                .filter(nota -> nota.getMatricula().getAluno().getId().equals(alunoId))
                .filter(nota -> nota.getMatricula().getDisciplina().getId().equals(disciplinaId))
                .filter(nota -> nota.getValorNota() != notaDTO.getValorNota())
                .findFirst()
                .orElse(null);

        if (notaAtualizada != null) {
            double novoValorNota = notaDTO.getValorNota();

            System.out.println(novoValorNota);

            if (novoValorNota < 0.0) {
                novoValorNota = 0.0;
            }

            if (novoValorNota > 10.0) {
                novoValorNota = 10.0;
            }

            notaAtualizada.setValorNota(novoValorNota);

            notaRepository.save(notaAtualizada);

            return new NotaDTO(
                    notaAtualizada.getId(),
                    notaAtualizada.getMatricula().getDisciplina().getId(),
                    notaAtualizada.getMatricula().getAluno().getNome(),
                    notaAtualizada.getMatricula().getDisciplina().getNome(),
                    notaAtualizada.getValorNota()
            );
        }

        return null;
    }
}
