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

O projeto disponibiliza o arquivo `application-example.properties`
como modelo de configuração.

No PowerShell, execute:

```powershell
Copy-Item .\src\main\resources\application-example.properties .\src\main\resources\application.properties
```

Configure as variáveis de ambiente com as credenciais do MySQL:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="SUA_SENHA"
```

Substitua os valores pelas credenciais do seu MySQL.

As variáveis de ambiente devem ser configuradas no mesmo terminal
utilizado para executar a aplicação e os testes.

**Importante:** o arquivo `application.properties` está listado
no `.gitignore` para evitar o envio de credenciais ao GitHub.


## Como executar

Certifique-se de que o serviço do MySQL esteja ativo e que
as variáveis de ambiente estejam configuradas.

Na pasta raiz do projeto, execute no PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

O projeto utiliza o Maven Wrapper, dispensando a instalação
manual do Maven.

A API e a interface web estarão disponíveis em:

http://localhost:8080

A interface permite gerenciar alunos, instrutores, aulas,
turmas e matrículas diretamente pelo navegador.

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
.\mvnw.cmd clean test
```

Os testes utilizam o banco de dados MySQL configurado na aplicação.
Recomenda-se utilizar um banco exclusivo para testes, evitando
alterações indesejadas nos dados de utilização do sistema.


## Tratamento de exceções

O projeto utiliza a classe `GlobalExceptionHandler` para
centralizar o tratamento de exceções do tipo `ResponseStatusException`,
utilizando as anotações `@RestControllerAdvice` e `@ExceptionHandler`.

O sistema retorna códigos HTTP acompanhados de mensagens
personalizadas.

| Código HTTP | Mensagem |
|---|---|
| 404 | Aluno não encontrado |
| 404 | Turma não encontrada |
| 409 | Aluno já está matriculado nesta turma |
| 409 | Turma sem vagas disponíveis |
| 409 | Aluno não está matriculado nesta turma |

A interface web interpreta essas respostas e apresenta mensagens claras ao usuário, facilitando a utilização do sistema.


## Autoria

Projeto acadêmico desenvolvido para a disciplina de Programação
Orientada a Objetos.
