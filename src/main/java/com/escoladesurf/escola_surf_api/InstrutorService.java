package com.escoladesurf.escola_surf_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    private final InstrutorRepository repository;

    public InstrutorService(InstrutorRepository repository) {
        this.repository = repository;
    }

    public List<Instrutor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Instrutor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Instrutor salvar(Instrutor instrutor) {
        return repository.save(instrutor);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}