package com.escoladesurf.escola_surf_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    private final AulaRepository repository;

    public AulaService(AulaRepository repository) {
        this.repository = repository;
    }

    public List<Aula> listarTodas() {
        return repository.findAll();
    }

    public Optional<Aula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Aula salvar(Aula aula) {
        return repository.save(aula);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Aula> buscarPorInstrutor(Long instrutorId) {
        return repository.findByInstrutorId(instrutorId);
    }
}
