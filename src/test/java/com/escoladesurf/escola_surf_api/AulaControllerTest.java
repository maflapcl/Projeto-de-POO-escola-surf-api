package com.escoladesurf.escola_surf_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AulaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    private Instrutor criarInstrutor() {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Instrutor Teste");
        instrutor.setEmail("instrutor.teste@email.com");
        instrutor.setEspecialidade("Surf");

        return instrutorRepository.save(instrutor);
    }

    @Test
    void deveListarAulas() throws Exception {
        mockMvc.perform(get("/aulas"))
                .andExpect(status().isOk());
    }

    @Test
    void deveCadastrarAula() throws Exception {
        Instrutor instrutor = criarInstrutor();

        String json = """
                {
                    "nome": "Aula Teste",
                    "descricao": "Aula para iniciantes",
                    "data": "2026-11-30",
                    "horario": "10:00:00",
                    "duracaoMinutos": 60,
                    "instrutor": {
                        "id": %d
                    }
                }
                """.formatted(instrutor.getId());

        mockMvc.perform(post("/aulas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Aula Teste"));
    }

    @Test
    void deveBuscarAulaPorId() throws Exception {
        Instrutor instrutor = criarInstrutor();

        Aula aula = new Aula();
        aula.setNome("Aula Busca");
        aula.setDescricao("Teste");
        aula.setData(java.time.LocalDate.of(2026, 11, 30));
        aula.setHorario(java.time.LocalTime.of(10, 0));
        aula.setDuracaoMinutos(60);
        aula.setInstrutor(instrutor);

        Aula salva = aulaRepository.save(aula);

        mockMvc.perform(get("/aulas/" + salva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aula Busca"));
    }

    @Test
    void deveAtualizarAula() throws Exception {
        Instrutor instrutor = criarInstrutor();

        Aula aula = new Aula();
        aula.setNome("Nome Antigo");
        aula.setDescricao("Descricao antiga");
        aula.setData(java.time.LocalDate.of(2026, 11, 30));
        aula.setHorario(java.time.LocalTime.of(10, 0));
        aula.setDuracaoMinutos(60);
        aula.setInstrutor(instrutor);

        Aula salva = aulaRepository.save(aula);

        String json = """
                {
                    "nome": "Aula Atualizada",
                    "descricao": "Descricao atualizada",
                    "data": "2026-12-01",
                    "horario": "14:00:00",
                    "duracaoMinutos": 90,
                    "instrutor": {
                        "id": %d
                    }
                }
                """.formatted(instrutor.getId());

        mockMvc.perform(put("/aulas/" + salva.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aula Atualizada"));
    }

    @Test
    void deveExcluirAula() throws Exception {
        Instrutor instrutor = criarInstrutor();

        Aula aula = new Aula();
        aula.setNome("Aula Excluir");
        aula.setDescricao("Teste");
        aula.setData(java.time.LocalDate.of(2026, 11, 30));
        aula.setHorario(java.time.LocalTime.of(10, 0));
        aula.setDuracaoMinutos(60);
        aula.setInstrutor(instrutor);

        Aula salva = aulaRepository.save(aula);

        mockMvc.perform(delete("/aulas/" + salva.getId()))
                .andExpect(status().isNoContent());

        assertFalse(aulaRepository.existsById(salva.getId()));
    }

    @Test
    void deveBuscarAulasPorInstrutor() throws Exception {
        Instrutor instrutor = criarInstrutor();

        Aula aula = new Aula();
        aula.setNome("Aula do Instrutor");
        aula.setDescricao("Teste");
        aula.setData(java.time.LocalDate.of(2026, 11, 30));
        aula.setHorario(java.time.LocalTime.of(10, 0));
        aula.setDuracaoMinutos(60);
        aula.setInstrutor(instrutor);

        aulaRepository.save(aula);

        mockMvc.perform(get("/aulas/instrutor/" + instrutor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Aula do Instrutor"));
    }

    @Test
    void naoDeveCadastrarAulaSemInstrutor() throws Exception {
        String json = """
                {
                    "nome": "Aula Sem Instrutor",
                    "descricao": "Teste",
                    "data": "2026-11-30",
                    "horario": "10:00:00",
                    "duracaoMinutos": 60
                }
                """;

        mockMvc.perform(post("/aulas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCadastrarAulaComInstrutorInexistente() throws Exception {
        String json = """
                {
                    "nome": "Aula Instrutor Inexistente",
                    "descricao": "Teste",
                    "data": "2026-11-30",
                    "horario": "10:00:00",
                    "duracaoMinutos": 60,
                    "instrutor": {
                        "id": 999999
                    }
                }
                """;

        mockMvc.perform(post("/aulas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void naoDeveAtualizarAulaInexistente() throws Exception {
        Instrutor instrutor = criarInstrutor();

        String json = """
                {
                    "nome": "Aula Atualizada",
                    "descricao": "Teste",
                    "data": "2026-11-30",
                    "horario": "10:00:00",
                    "duracaoMinutos": 60,
                    "instrutor": {
                        "id": %d
                    }
                }
                """.formatted(instrutor.getId());

        mockMvc.perform(put("/aulas/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
