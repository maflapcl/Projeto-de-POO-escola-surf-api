# 🌊 Escola de Surf API

API REST desenvolvida em Java com Spring Boot e MySQL para o
gerenciamento de uma escola de surf.

Projeto acadêmico que aplica conceitos de Programação Orientada a
Objetos, herança, relacionamentos entre entidades e arquitetura em
camadas.

## Tecnologias utilizadas

-   Java 21
-   Spring Boot 4.1.2
-   Spring Data JPA
-   Hibernate
-   MySQL 8
-   Maven
-   JUnit e MockMvc
-   Git e GitHub

## Entidades e relacionamentos

O sistema possui quatro entidades concretas: Aluno, Instrutor, Turma e
Aula.

### Herança

A classe abstrata `Pessoa` é a classe base de `Aluno` e `Instrutor`,
utilizando a estratégia `JOINED` do JPA.

### Relacionamentos

-   **Instrutor e Aula (1:N):** um instrutor pode ministrar várias
    aulas, e cada aula está associada a um instrutor.
-   **Aluno e Turma (N:N):** um aluno pode participar de várias turmas,
    e uma turma pode ter vários alunos. A associação utiliza a tabela
    intermediária `aluno_turma`.

## Funcionalidades

-   CRUD completo de alunos.
-   CRUD completo de instrutores.
-   CRUD completo de turmas.
-   CRUD completo de aulas.
-   Matrícula e remoção de alunos em turmas.
-   Consulta de aulas por instrutor.

## Endpoints disponíveis

### Alunos --- `/alunos`

  Método   Endpoint         Operação
  -------- ---------------- ---------------------
  GET      `/alunos`        Listar alunos
  GET      `/alunos/{id}`   Buscar aluno por ID
  POST     `/alunos`        Cadastrar aluno
  PUT      `/alunos/{id}`   Atualizar aluno
  DELETE   `/alunos/{id}`   Excluir aluno

### Instrutores --- `/instrutores`

  Método   Endpoint              Operação
  -------- --------------------- -------------------------
  GET      `/instrutores`        Listar instrutores
  GET      `/instrutores/{id}`   Buscar instrutor por ID
  POST     `/instrutores`        Cadastrar instrutor
  PUT      `/instrutores/{id}`   Atualizar instrutor
  DELETE   `/instrutores/{id}`   Excluir instrutor

### Turmas --- `/turmas`

  Método   Endpoint                               Operação
  -------- -------------------------------------- ---------------------
  GET      `/turmas`                              Listar turmas
  GET      `/turmas/{id}`                         Buscar turma por ID
  POST     `/turmas`                              Cadastrar turma
  PUT      `/turmas/{id}`                         Atualizar turma
  DELETE   `/turmas/{id}`                         Excluir turma
  POST     `/turmas/{turmaId}/alunos/{alunoId}`   Matricular aluno
  DELETE   `/turmas/{turmaId}/alunos/{alunoId}`   Remover aluno

### Aulas --- `/aulas`

  ----------------------------------------------------------------------------------
  Método                  Endpoint                           Operação
  ----------------------- ---------------------------------- -----------------------
  GET                     `/aulas`                           Listar aulas

  GET                     `/aulas/{id}`                      Buscar aula por ID

  POST                    `/aulas`                           Cadastrar aula

  PUT                     `/aulas/{id}`                      Atualizar aula

  DELETE                  `/aulas/{id}`                      Excluir aula

  GET                     `/aulas/instrutor/{instrutorId}`   Consultar aulas por
                                                             instrutor
  ----------------------------------------------------------------------------------

## Configuração do ambiente

### Pré-requisitos

-   JDK 21
-   MySQL 8

### Banco de dados

Crie o banco de dados:

``` sql
CREATE DATABASE escola_surf;
```

### application.properties

Crie o arquivo `src/main/resources/application.properties` com suas
configurações locais:

``` properties
spring.application.name=escola-surf-api
spring.datasource.url=jdbc:mysql://localhost:3306/escola_surf
spring.datasource.username=root
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Substitua `SUA_SENHA` pela senha do seu MySQL.

**Importante:** o arquivo `application.properties` deve permanecer local
e está listado no `.gitignore` para evitar o envio de credenciais ao
GitHub.

## Como executar

Na pasta raiz do projeto, execute:

``` powershell
cmd /c mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Testes automatizados

Os testes utilizam JUnit e MockMvc.

  Classe de teste                   Testes aprovados
  ------------------------------- ------------------
  AlunoControllerTest                              5
  AulaControllerTest                               9
  InstrutorControllerTest                          5
  TurmaControllerTest                              7
  EscolaSurfApiApplicationTests                    1
  **Total**                                   **27**

**Resultado da última execução:** 27 testes aprovados, sem falhas ou
erros.

Para executar os testes novamente:

``` powershell
cmd /c mvnw.cmd test
```

## Autoria

Projeto acadêmico desenvolvido para a disciplina de Programação
Orientada a Objetos.
