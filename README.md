# 🌊 Escola de Surf API

API REST desenvolvida em Java com Spring Boot e MySQL para o gerenciamento de uma escola de surf.

O projeto tem como objetivo facilitar o gerenciamento de alunos, instrutores, turmas, aulas e matrículas, aplicando conceitos de Programação Orientada a Objetos e desenvolvimento de APIs REST.

## 🎯 Objetivos do projeto

- Cadastrar e gerenciar alunos e instrutores.
- Organizar turmas e aulas de surf.
- Realizar matrículas de alunos nas turmas.
- Relacionar instrutores às aulas.
- Aplicar conceitos de herança e relacionamentos entre entidades.
- Implementar operações CRUD completas.

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
- Total: 11 testes aprovados, sem falhas.

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
| POST | `/instrutores` | Cadastrar instrutor |
| PUT | `/instrutores/{id}` | Atualizar instrutor |
| DELETE | `/instrutores/{id}` | Excluir instrutor |

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java JDK 21.
- MySQL instalado e configurado.
- Git.

### 1. Clonar o repositório

```bash
git clone https://github.com/maflapcl/Projeto-de-POO---escola-surf-api.git
```

### 2. Criar o banco de dados

No MySQL, execute:

```sql
CREATE DATABASE escola_surf;
```

### 3. Configurar a conexão

Na pasta `src/main/resources`, copie o arquivo `application-example.properties` e renomeie a cópia para `application.properties`.

Configure seu usuário e sua senha do MySQL nesse arquivo.

**Importante:** o arquivo `application.properties` contém configurações locais e não deve ser enviado ao GitHub.

### 4. Executar a aplicação

No terminal, dentro da pasta do projeto, execute:

```bash
.\mvnw.cmd spring-boot:run
```

A API será iniciada localmente na porta `8080`.

## 🧪 Executar os testes

Para executar os testes automatizados:

```bash
.\mvnw.cmd test
```

## 🚧 Próximas etapas

O projeto está em desenvolvimento colaborativo. As próximas etapas incluem:

- Implementar o CRUD de Turmas.
- Implementar o relacionamento N:N entre Alunos e Turmas.
- Desenvolver as operações de matrícula e remoção de alunos.
- Implementar o CRUD de Aulas.
- Implementar o relacionamento 1:N entre Instrutores e Aulas.
- Realizar a integração e os testes gerais da API.

## 👥 Desenvolvimento em equipe

Projeto acadêmico desenvolvido em grupo como parte da disciplina de Programação Orientada a Objetos.

**Repositório:** [Escola de Surf API](https://github.com/maflapcl/Projeto-de-POO---escola-surf-api)

---

*Projeto em desenvolvimento — a documentação será atualizada conforme novas funcionalidades forem integradas.*
