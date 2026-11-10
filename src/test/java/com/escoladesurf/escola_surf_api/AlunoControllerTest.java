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
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void deveListarAlunos() throws Exception {
        mockMvc.perform(get("/alunos"))
                .andExpect(status().isOk());
    }

    @Test
    void deveCadastrarAluno() throws Exception {
        String alunoJson = """
                {
                    "nome": "Aluno Teste",
                    "email": "alunoteste@email.com",
                    "nivel": "Iniciante"
                }
                """;

        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aluno Teste"))
                .andExpect(jsonPath("$.email").value("alunoteste@email.com"))
                .andExpect(jsonPath("$.nivel").value("Iniciante"));
    }

    @Test
    void deveBuscarAlunoPorId() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Busca");
        aluno.setEmail("busca@email.com");
        aluno.setNivel("Intermediario");

        Aluno salvo = alunoRepository.save(aluno);

        mockMvc.perform(get("/alunos/" + salvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aluno Busca"))
                .andExpect(jsonPath("$.email").value("busca@email.com"));
    }

    @Test
    void deveAtualizarAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Nome Antigo");
        aluno.setEmail("antigo@email.com");
        aluno.setNivel("Iniciante");

        Aluno salvo = alunoRepository.save(aluno);

        String alunoJson = """
                {
                    "nome": "Nome Atualizado",
                    "email": "atualizado@email.com",
                    "nivel": "Avancado"
                }
                """;

        mockMvc.perform(put("/alunos/" + salvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"))
                .andExpect(jsonPath("$.email").value("atualizado@email.com"))
                .andExpect(jsonPath("$.nivel").value("Avancado"));
    }

    @Test
    void deveExcluirAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Excluir");
        aluno.setEmail("excluir@email.com");
        aluno.setNivel("Iniciante");

        Aluno salvo = alunoRepository.save(aluno);

        mockMvc.perform(delete("/alunos/" + salvo.getId()))
                .andExpect(status().isOk());

        assertFalse(alunoRepository.existsById(salvo.getId()));
    }
}