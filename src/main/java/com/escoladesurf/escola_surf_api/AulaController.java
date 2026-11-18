package com.escoladesurf.escola_surf_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService service;
    private final InstrutorService instrutorService;

    public AulaController(AulaService service, InstrutorService instrutorService) {
        this.service = service;
        this.instrutorService = instrutorService;
    }

    @GetMapping
    public List<Aula> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Aula aula) {

        if (aula.getInstrutor() == null || aula.getInstrutor().getId() == null) {
            return ResponseEntity.badRequest().body("Informe o ID do instrutor.");
        }

        Instrutor instrutor = instrutorService
                .buscarPorId(aula.getInstrutor().getId())
                .orElse(null);

        if (instrutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instrutor não encontrado.");
        }

        aula.setInstrutor(instrutor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(aula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Aula aula) {

        Aula aulaExistente = service.buscarPorId(id).orElse(null);

        if (aulaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        if (aula.getInstrutor() == null || aula.getInstrutor().getId() == null) {
            return ResponseEntity.badRequest().body("Informe o ID do instrutor.");
        }

        Instrutor instrutor = instrutorService
                .buscarPorId(aula.getInstrutor().getId())
                .orElse(null);

        if (instrutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instrutor não encontrado.");
        }

        aulaExistente.setNome(aula.getNome());
        aulaExistente.setDescricao(aula.getDescricao());
        aulaExistente.setData(aula.getData());
        aulaExistente.setHorario(aula.getHorario());
        aulaExistente.setDuracaoMinutos(aula.getDuracaoMinutos());
        aulaExistente.setInstrutor(instrutor);

        return ResponseEntity.ok(service.salvar(aulaExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/instrutor/{instrutorId}")
    public List<Aula> buscarPorInstrutor(@PathVariable Long instrutorId) {
        return service.buscarPorInstrutor(instrutorId);
    }
}
