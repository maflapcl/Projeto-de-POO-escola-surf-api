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
class InstrutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Test
    void deveListarInstrutores() throws Exception {
        mockMvc.perform(get("/instrutores"))
                .andExpect(status().isOk());
    }

    @Test
    void deveCadastrarInstrutor() throws Exception {
        String instrutorJson = """
                {
                    "nome": "Instrutor Teste",
                    "email": "instrutorteste@email.com",
                    "especialidade": "Surf para iniciantes"
                }
                """;

        mockMvc.perform(post("/instrutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(instrutorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Instrutor Teste"))
                .andExpect(jsonPath("$.email").value("instrutorteste@email.com"))
                .andExpect(jsonPath("$.especialidade").value("Surf para iniciantes"));
    }

    @Test
    void deveBuscarInstrutorPorId() throws Exception {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Instrutor Busca");
        instrutor.setEmail("buscainstrutor@email.com");
        instrutor.setEspecialidade("Surf intermediario");

        Instrutor salvo = instrutorRepository.save(instrutor);

        mockMvc.perform(get("/instrutores/" + salvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Instrutor Busca"))
                .andExpect(jsonPath("$.email").value("buscainstrutor@email.com"))
                .andExpect(jsonPath("$.especialidade").value("Surf intermediario"));
    }

    @Test
    void deveAtualizarInstrutor() throws Exception {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Nome Antigo");
        instrutor.setEmail("antigoinstrutor@email.com");
        instrutor.setEspecialidade("Iniciantes");

        Instrutor salvo = instrutorRepository.save(instrutor);

        String instrutorJson = """
                {
                    "nome": "Nome Atualizado",
                    "email": "atualizadoinstrutor@email.com",
                    "especialidade": "Surf avancado"
                }
                """;

        mockMvc.perform(put("/instrutores/" + salvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(instrutorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"))
                .andExpect(jsonPath("$.email").value("atualizadoinstrutor@email.com"))
                .andExpect(jsonPath("$.especialidade").value("Surf avancado"));
    }

    @Test
    void deveExcluirInstrutor() throws Exception {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Instrutor Excluir");
        instrutor.setEmail("excluirinstrutor@email.com");
        instrutor.setEspecialidade("Iniciantes");

        Instrutor salvo = instrutorRepository.save(instrutor);

        mockMvc.perform(delete("/instrutores/" + salvo.getId()))
                .andExpect(status().isOk());

        assertFalse(instrutorRepository.existsById(salvo.getId()));
    }
}