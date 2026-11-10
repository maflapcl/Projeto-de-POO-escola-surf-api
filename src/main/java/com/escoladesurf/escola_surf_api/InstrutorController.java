
package com.escoladesurf.escola_surf_api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    private final InstrutorService service;

    public InstrutorController(InstrutorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instrutor> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Instrutor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Instrutor cadastrar(@RequestBody Instrutor instrutor) {
        return service.salvar(instrutor);
    }

    @PutMapping("/{id}")
    public Instrutor atualizar(
            @PathVariable Long id,
            @RequestBody Instrutor instrutor) {

        return service.buscarPorId(id).map(instrutorExistente -> {
            instrutorExistente.setNome(instrutor.getNome());
            instrutorExistente.setEmail(instrutor.getEmail());
            instrutorExistente.setEspecialidade(instrutor.getEspecialidade());

            return service.salvar(instrutorExistente);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
