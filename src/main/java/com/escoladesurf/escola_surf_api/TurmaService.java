
package com.escoladesurf.escola_surf_api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;

    public TurmaService(TurmaRepository turmaRepository,
                        AlunoRepository alunoRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
    }

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Turma não encontrada"));
    }

    @Transactional
    public Turma cadastrar(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma atualizar(Long id, Turma dados) {
        Turma turma = buscarPorId(id);

        turma.setNome(dados.getNome());
        turma.setDescricao(dados.getDescricao());
        turma.setCapacidade(dados.getCapacidade());

        return turmaRepository.save(turma);
    }

    @Transactional
    public void excluir(Long id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }

    // MATRICULAR ALUNO EM UMA TURMA
    @Transactional
    public Turma matricularAluno(Long turmaId, Long alunoId) {

        Turma turma = buscarPorId(turmaId);

        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        if (turma.getAlunos().contains(aluno)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Aluno já está matriculado nesta turma");
        }

        if (turma.getCapacidade() == null ||
            turma.getCapacidade() <= 0 ||
            turma.getAlunos().size() >= turma.getCapacidade()) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Turma sem vagas disponíveis");
        }

        turma.getAlunos().add(aluno);

        return turmaRepository.save(turma);
    }

@Transactional
public Turma removerAluno(Long turmaId, Long alunoId) {

    Turma turma = buscarPorId(turmaId);

    Aluno aluno = alunoRepository.findById(alunoId)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Aluno não encontrado"));

    if (!turma.getAlunos().contains(aluno)) {
        throw new ResponseStatusException(
            HttpStatus.CONFLICT,
            "Aluno não está matriculado nesta turma");
    }

    turma.getAlunos().remove(aluno);

    return turmaRepository.save(turma);
}


}
