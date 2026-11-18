package com.escoladesurf.escola_surf_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Long id,
            @RequestBody Aluno aluno) {

        Aluno alunoExistente = service.buscarPorId(id).orElse(null);

        if (alunoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setEmail(aluno.getEmail());
        alunoExistente.setNivel(aluno.getNivel());

        return ResponseEntity.ok(service.salvar(alunoExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}