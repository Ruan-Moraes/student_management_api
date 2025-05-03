# Sistema de Gerenciamento Acadêmico - Backend

## Descrição

Este é o **backend** de um sistema de gerenciamento acadêmico desenvolvido com **Spring Boot**. Ele permite:

- Gerenciar alunos e disciplinas.
- Realizar matrículas.
- Registrar e atualizar notas.
- Calcular médias e identificar alunos com baixo desempenho ou frequência.

## Tecnologias Utilizadas

- **Java 21**
- **Maven**
- **Spring Boot**
- **Spring Data JPA**
- **Spring MVC**
- **Spring HATEOAS**
- **H2 Database**
- **Swagger**
- **MapStruct**

## Estrutura do Projeto

- `config/` - Configurações do projeto.
- `controllers/` - Camada de entrada da aplicação (REST).
- `controllers/docs/` - Documentação Swagger.
- `models/` - Modelos de dados (entidades).
- `services/` - Contém a lógica de negócio.
- `repositories/` - Comunicação com o banco de dados.
- `exceptions/` - Tratamento de exceções.
- `dtos/` - Objetos de transferência de dados (request/response).
- `mappers/` - Mapeamento entre entidades e DTOs.
- `hateoas/` - Implementação do HATEOAS.'

## Endpoints da API

### Alunos (`/students`)

| Método | Endpoint                 | Descrição                                |
|--------|--------------------------|------------------------------------------|
| GET    | `/students`              | Lista todos os alunos                    |
| GET    | `/students/{id}`         | Busca um aluno por ID                    |
| POST   | `/students`              | Cadastra um novo aluno                   |
| PUT    | `/students/{id}`         | Atualiza os dados de um aluno            |
| DELETE | `/students/{id}`         | Remove um aluno                          |
| GET    | `/students/lowFrequency` | Lista alunos com frequência abaixo de X% |

### Disciplinas (`/disciplines`)

| Método | Endpoint            | Descrição                           |
|--------|---------------------|-------------------------------------|
| GET    | `/disciplines`      | Lista todas as disciplinas          |
| GET    | `/disciplines/{id}` | Busca uma disciplina por ID         |
| POST   | `/disciplines`      | Cadastra uma nova disciplina        |
| PUT    | `/disciplines/{id}` | Atualiza os dados de uma disciplina |
| DELETE | `/disciplines/{id}` | Remove uma disciplina               |

### Matrículas (`/enrollments`)

| Método | Endpoint                                      | Descrição                                    |
|--------|-----------------------------------------------|----------------------------------------------|
| GET    | `/enrollments`                                | Lista todas as matrículas                    |
| GET    | `/enrollments/{id}`                           | Busca uma matrícula por ID                   |
| GET    | `/enrollments/FindByStudentIdAndDisciplineId` | Busca matrícula por ID de aluno e disciplina |
| POST   | `/enrollments`                                | Cria uma nova matrícula                      |
| DELETE | `/enrollments/{id}`                           | Remove uma matrícula                         |

### Notas (`/grades`)

| Método | Endpoint                                       | Descrição                                            |
|--------|------------------------------------------------|------------------------------------------------------|
| GET    | `/grades`                                      | Lista todas as notas com dados de aluno e disciplina |
| GET    | `/grades/findAllGradesByStudentId/{studentId}` | Lista notas de um aluno específico                   |
| GET    | `/grades/findAverageForEachStudent`            | Retorna a média de cada aluno                        |
| GET    | `/grades/findAverageStudentById/{studentId}`   | Retorna a média de um aluno específico               |
| GET    | `/grades/findAboveAverageStudents`             | Lista alunos com desempenho acima da média           |
| GET    | `/grades/calculateAverageAllGrades`            | Calcula a média geral de todas as notas              |
| GET    | `/grades/averageGradesByDiscipline`            | Calcula a média das notas por disciplina             |
| POST   | `/grades?studentId=X&disciplineId=Y`           | Registra uma nota para um aluno em uma disciplina    |
| PUT    | `/grades?studentId=X&disciplineId=Y`           | Atualiza a nota de um aluno em uma disciplina        |

## Frontend

O frontend deste projeto está disponível no repositório:
👉 [student-management-ui](https://github.com/Ruan-Moraes/student-management-ui)

## Requisitos

- Java 21
- Maven 3.9+

## Como Rodar o Projeto

1. **Clone o repositório**:

```bash
git clone https://github.com/Ruan-Moraes/student_management_api
cd student_management_api
```

2. **Abra na sua IDE** (IntelliJ, Eclipse, VS Code com extensão Java).

3. **Execute a classe principal**:

```
src/main/java/com/example/studentManagementApi/StudentManagementApiApplication.java
```

4. **Acesse a aplicação** em:

```
http://localhost:8080
```

5. **Console do banco H2 (opcional)**:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:file:./data/DB`
- User: `student-management-api`
- Password: `student-management-api`

6. **Documentação Swagger**:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Observações

- A aplicação usa **H2 Database em memória**, mas os dados não são perdidos ao reiniciar. Fiz uma gambiarra para ganhar
  tempo.
- Não possui autenticação.

---

## Frontend

Repositório do frontend da aplicação:  
👉 [studentModel-management-ui](https://github.com/Ruan-Moraes/studentModel-management-ui)
