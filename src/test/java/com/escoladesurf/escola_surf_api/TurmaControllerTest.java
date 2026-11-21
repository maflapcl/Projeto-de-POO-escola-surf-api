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
class TurmaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void deveListarTurmas() throws Exception {
        mockMvc.perform(get("/turmas"))
                .andExpect(status().isOk());
    }

    @Test
    void deveCadastrarTurma() throws Exception {
        String json = """
                {
                    "nome": "Turma Teste",
                    "descricao": "Aulas para iniciantes",
                    "capacidade": 10
                }
                """;

        mockMvc.perform(post("/turmas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Turma Teste"));
    }

    @Test
    void deveBuscarTurmaPorId() throws Exception {
        Turma turma = new Turma();
        turma.setNome("Turma Busca");
        turma.setDescricao("Teste");
        turma.setCapacidade(8);

        Turma salva = turmaRepository.save(turma);

        mockMvc.perform(get("/turmas/" + salva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Turma Busca"));
    }

    @Test
    void deveAtualizarTurma() throws Exception {
        Turma turma = new Turma();
        turma.setNome("Nome Antigo");
        turma.setDescricao("Descricao antiga");
        turma.setCapacidade(5);

        Turma salva = turmaRepository.save(turma);

        String json = """
                {
                    "nome": "Turma Atualizada",
                    "descricao": "Descricao atualizada",
                    "capacidade": 15
                }
                """;

        mockMvc.perform(put("/turmas/" + salva.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Turma Atualizada"));
    }

    @Test
    void deveExcluirTurma() throws Exception {
        Turma turma = new Turma();
        turma.setNome("Turma Excluir");
        turma.setDescricao("Teste");
        turma.setCapacidade(10);

        Turma salva = turmaRepository.save(turma);

        mockMvc.perform(delete("/turmas/" + salva.getId()))
                .andExpect(status().isNoContent());

        assertFalse(turmaRepository.existsById(salva.getId()));
    }

    @Test
    void deveMatricularAlunoNaTurma() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Matricula");
        aluno.setEmail("matricula@email.com");
        aluno.setNivel("Iniciante");

        Aluno alunoSalvo = alunoRepository.save(aluno);

        Turma turma = new Turma();
        turma.setNome("Turma Matricula");
        turma.setDescricao("Teste");
        turma.setCapacidade(10);

        Turma turmaSalva = turmaRepository.save(turma);

        mockMvc.perform(post("/turmas/" + turmaSalva.getId()
                        + "/alunos/" + alunoSalvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alunos[0].nome").value("Aluno Matricula"));
    }

    @Test
    void deveRemoverAlunoDaTurma() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Remover");
        aluno.setEmail("remover@email.com");
        aluno.setNivel("Intermediario");

        Aluno alunoSalvo = alunoRepository.save(aluno);

        Turma turma = new Turma();
        turma.setNome("Turma Remover");
        turma.setDescricao("Teste");
        turma.setCapacidade(10);
        turma.getAlunos().add(alunoSalvo);

        Turma turmaSalva = turmaRepository.save(turma);

        mockMvc.perform(delete("/turmas/" + turmaSalva.getId()
                        + "/alunos/" + alunoSalvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alunos").isEmpty());
    }
}
