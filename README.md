# Sistema de Gerenciamento Acadêmico (Backend)

## Descrição

Este é o **backend** de um sistema de gerenciamento acadêmico desenvolvido em **Spring Boot**.

- Cadastrar, editar e excluir alunos e disciplinas.
- Matricular alunos em disciplinas.
- Registrar e atualizar notas dos alunos.
- Calcular médias de notas por aluno, disciplina e turma.
- Identificar alunos com frequência baixa e desempenho acima da média.

## Tecnologias Utilizadas

- **Java (Spring Boot)**
- **JPA (Java Persistence API)**
- **H2 Database**
- **Maven**

## Estrutura do Projeto

- **Controllers**: Gerenciam as requisições da API.
- **Services**: Contêm a lógica de negócios.
- **Repositories**: Fazem a comunicação com o banco de dados.
- **Entities**: Representam as tabelas do banco de dados.
- **DTOs**: Representam os objetos de transferência de dados.

## Endpoints da API

### Alunos (`/alunos`)

- **POST** `/cadastrar` - Cadastra um novo aluno.
- **GET** `/listar` - Lista todos os alunos.
- **PUT** `/atualizar/{id}` - Atualiza um aluno existente.
- **DELETE** `/deletar/{id}` - Deleta um aluno.
- **GET** `/buscar/frequencia/{frequencia}` - Lista alunos com frequência abaixo de um valor específico.

### Disciplinas (`/disciplinas`)

- **POST** `/cadastrar` - Cadastra uma nova disciplina.
- **GET** `/listar` - Lista todas as disciplinas.
- **PUT** `/atualizar/{id}` - Atualiza uma disciplina existente.
- **DELETE** `/deletar/{id}` - Deleta uma disciplina.

### Matrículas (`/matriculas`)

- **POST** `/criar` - Cria uma nova matrícula.
- **DELETE** `/remover/{id}` - Remove uma matrícula.

### Notas (`/notas`)

- **GET** `/alunos` - Retorna todas as notas dos alunos.
- **GET** `/alunos/{alunoId}` - Retorna as notas de um aluno específico.
- **GET** `/media-todos-alunos` - Calcula a média das notas de todos os alunos.
- **GET** `/media/{alunoId}` - Calcula a média das notas de um aluno específico.
- **GET** `/media-todos-alunos-disciplina` - Calcula a média das notas de todos os alunos por disciplina.
- **GET** `/alunos-acima-media-turma` - Retorna alunos com notas acima da média da turma.
- **PUT** `/atualizar/{alunoId}/{disciplinaId}` - Atualiza a nota de um aluno em uma disciplina específica.

## Frontend

O frontend do projeto pode ser encontrado clicando em [student-management-ui](https://github.com/Ruan-Moraes/student-management-ui).

## Observações

- Preferi utilizar o H2 Database para facilitar a execução do projeto.
- Não implementei autenticação, pois fugiria do escopo do projeto.
- Não fiz tratamento de exceções, e nem validação de dados, por questões de tempo.

## Requisitos

- Java 21
- Maven

## Como Rodar o Projeto

1. Clone o repositório

```bash
git clone https://github.com/Ruan-Moraes/student_management_api
```

2. Importe o projeto em sua IDE de preferência.

3. Execute a classe `StudentManagementApiApplication`.
