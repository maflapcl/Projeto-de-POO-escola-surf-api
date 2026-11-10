# 🌊 Escola de Surf API

A **Escola de Surf API** é um projeto acadêmico desenvolvido em Java com Spring Boot e MySQL, com o objetivo de criar uma API REST para o gerenciamento de uma escola de surf.

A proposta é oferecer uma estrutura para organizar informações de alunos, instrutores, turmas, aulas e matrículas, facilitando o gerenciamento das atividades de uma escola de surf.

O desenvolvimento aplica conceitos de Programação Orientada a Objetos, arquitetura em camadas, persistência de dados e comunicação por meio de endpoints HTTP.

## 🎯 Objetivos do projeto

- Cadastrar e gerenciar alunos e instrutores.
- Organizar turmas e aulas de surf.
- Realizar matrículas de alunos nas turmas.
- Relacionar instrutores às aulas.
- Aplicar conceitos de herança e relacionamentos entre entidades.
- Implementar operações CRUD completas (inserção, consulta, alteração e exclusão).

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.1.2**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**
- **Postman**
- **Git e GitHub**

## 📌 Funcionalidades implementadas

### Alunos e Instrutores

- Classe abstrata `Pessoa`, utilizada como classe base.
- Herança entre `Pessoa`, `Aluno` e `Instrutor`.
- CRUD completo de alunos.
- CRUD completo de instrutores.
- Separação da aplicação em Controllers, Services e Repositories.

### Testes automatizados

Foram implementados testes utilizando JUnit e MockMvc para verificar os endpoints da API.

**Resultado atual:**

- 5 testes de Aluno aprovados.
- 5 testes de Instrutor aprovados.
- 1 teste de inicialização da aplicação aprovado.
- **Total: 11 testes aprovados, sem falhas ou erros.**

## 🔗 Endpoints disponíveis

### Alunos — `/alunos`

| Método HTTP | Endpoint | Operação |
|---|---|---|
| GET | `/alunos` | Listar alunos |
| GET | `/alunos/{id}` | Buscar aluno por ID |
| POST | `/alunos` | Cadastrar aluno |
| PUT | `/alunos/{id}` | Atualizar aluno |
| DELETE | `/alunos/{id}` | Excluir aluno |

### Instrutores — `/instrutores`

| Método HTTP | Endpoint | Operação |
|---|---|---|
| GET | `/instrutores` | Listar instrutores |
| GET | `/instrutores/{id}` | Buscar instrutor por ID |
| POST | `/instr
