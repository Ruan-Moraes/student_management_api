package com.ruanmoraes.student_management_api.services;

import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
//    private final DisciplinaRepository disciplinaRepository;
//
//    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
//        this.disciplinaRepository = disciplinaRepository;
//    }
//
//    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
//        return disciplinaRepository.save(disciplina);
//    }
//
//    public List<Disciplina> listarTodasDisciplinas() {
//        return disciplinaRepository.findAll();
//    }
//
//    public Disciplina atualizarDisciplinaPorId(Long id, Disciplina disciplina) {
//        Disciplina disciplinaAtualizada = disciplinaRepository.findById(id).orElse(null);
//
//        if (disciplinaAtualizada != null) {
//            disciplinaAtualizada.setNome(disciplina.getNome());
//
//            return disciplinaRepository.save(disciplinaAtualizada);
//        }
//
//        return null;
//    }
//
//    public boolean deletarDisciplinaPorId(Long id) {
//        if (disciplinaRepository.existsById(id)) {
//            disciplinaRepository.deleteById(id);
//
//            return true;
//        }
//
//        return false;
//    }
}
