
package com.escoladesurf.escola_surf_api;

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
    public Aluno buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return service.salvar(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(
            @PathVariable Long id,
            @RequestBody Aluno aluno) {

        return service.buscarPorId(id).map(alunoExistente -> {
            alunoExistente.setNome(aluno.getNome());
            alunoExistente.setEmail(aluno.getEmail());
            alunoExistente.setNivel(aluno.getNivel());

            return service.salvar(alunoExistente);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
