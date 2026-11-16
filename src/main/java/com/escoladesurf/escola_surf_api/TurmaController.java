
package com.escoladesurf.escola_surf_api;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    // Listar todas as turmas
    @GetMapping
    public List<Turma> listarTodas() {
        return turmaService.listarTodas();
    }

    // Buscar turma pelo ID
    @GetMapping("/{id}")
    public Turma buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorId(id);
    }

    // Cadastrar uma turma
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma cadastrar(@RequestBody Turma turma) {
        return turmaService.cadastrar(turma);
    }

    // Atualizar uma turma
    @PutMapping("/{id}")
    public Turma atualizar(
            @PathVariable Long id,
            @RequestBody Turma turma) {

        return turmaService.atualizar(id, turma);
    }

    // Excluir uma turma
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        turmaService.excluir(id);
    }


    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public Turma matricularAluno(
            @PathVariable Long turmaId,
            @PathVariable Long alunoId) {

        return turmaService.matricularAluno(turmaId, alunoId);
    }


@DeleteMapping("/{turmaId}/alunos/{alunoId}")
public Turma removerAluno(
        @PathVariable Long turmaId,
        @PathVariable Long alunoId) {

    return turmaService.removerAluno(turmaId, alunoId);
}


}
