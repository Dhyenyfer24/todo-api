
# Todo API

API REST para gerenciamento de tarefas desenvolvida com Java e Spring Boot.

O projeto permite o cadastro de usuários, autenticação via JWT e gerenciamento seguro de tarefas, garantindo que cada usuário tenha acesso apenas aos seus próprios registros.

Além disso, a aplicação conta com documentação Swagger, testes unitários e suporte a Docker, facilitando a execução e o desenvolvimento em diferentes ambientes.

## Tecnologias

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- JWT
- PostgreSQL
- Maven
- Docker
- Mockito
- Swagger
- JUnit 5

## Funcionalidades

###Autenticação e Segurança

- Cadastro de usuários
- Login com JWT
- Proteção de rotas com Spring Security
- Controle de acesso baseado no usuário autenticado

  ### Gerenciamento de Tarefas
  
- Criar tarefas
- Listar tarefas do usuário autenticado
- Buscar tarefa por ID
- Atualizar informaçoes de uma tarefa
- Controle de status (TODO / DONE)
- Excluir tarefas

### Qualidade da aplicação

- Validação de dados de entrada
- Tratamento global de exceções
- Documentação automática com Swagger/OpenAPI
- Testes unitários

## Endpoints

### Authentication

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Authenticate user and generate JWT token |

### Tasks

| Method | Endpoint | Description |
|----------|----------|-------------|
| GET | /tasks | List authenticated user's tasks |
| GET | /tasks/{id} | Get task by ID |
| POST | /tasks | Create a new task |
| PUT | /tasks/{id} | Update task information |
| PATCH | /tasks/{id}/status | Update task status |
| DELETE | /tasks/{id} | Delete task |

## Autenticação

A API utiliza JWT (JSON Web Token) para autenticação e autorização dos usuários.

Para acessar os endpoints protegidos, é necessário realizar o cadastro e efetuar login para obter um token válido.

### Cadastro

```http
POST /auth/register
```

Exemplo de requisição:

```json
{
  "name": "Luiza",
  "email": "Luiza@email.com",
  "password": "123456"
}
```

### Login

```http
POST /auth/login
```

Exemplo de requisição:

```json
{
  "email": "Luiza@email.com",
  "password": "123456"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Utilizando o Token

Após realizar o login, o token deve ser enviado no cabeçalho das requisições:

```http
Authorization: Bearer <token>
```

Esse token é obrigatório para acessar os endpoints protegidos de tarefas.

## Executando com Docker

O projeto possui suporte a Docker e Docker Compose, permitindo a execução da API e do banco de dados PostgreSQL sem a necessidade de instalações adicionais.

### Pré-requisitos

- Docker
- Docker Compose

### Executando a aplicação

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Após a inicialização dos containers, a API estará disponível em:

```text
http://localhost:8080
```

### Acessando a documentação

A documentação da API pode ser acessada através do Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

### Encerrando os containers

Para parar a aplicação, execute:

```bash
docker compose down
```
## 🧪 Executando os Testes

O projeto possui testes unitários desenvolvidos com JUnit 5 e Mockito para validar as principais regras de negócio da aplicação.

### Executando todos os testes

Na raiz do projeto, execute:

```bash
./mvnw test
```

ou, no Windows:

```bash
mvnw.cmd test
```

### Cobertura atual

Os testes implementados validam cenários como:

- Criação de tarefas
- Atualização de status
- Busca de tarefas por ID
- Exclusão de tarefas
- Tratamento de tarefas não encontradas

### Tecnologias utilizadas nos testes

- JUnit 5
- Mockito
  
##  Estrutura do Projeto

O projeto segue uma arquitetura em camadas, separando responsabilidades para facilitar a manutenção, evolução e testes da aplicação.

```text
src/main/java/com/dhyenyfer/todoapi
├── controller     # Endpoints da API
├── dto            # Objetos de entrada e saída
├── entity         # Entidades JPA
├── enums          # Enumerações da aplicação
├── exception      # Tratamento de exceções
├── repository     # Acesso aos dados
├── security       # Configurações de segurança e JWT
├── service        # Regras de negócio
└── TodoApiApplication.java
```

### Responsabilidades das Camadas

**Controller**
- Recebe as requisições HTTP.
- Encaminha as operações para a camada de serviço.
- Retorna as respostas da API.

**Service**
- Contém as regras de negócio da aplicação.
- Realiza validações e processamento dos dados.

**Repository**
- Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

**DTO**
- Define os objetos utilizados para entrada e saída de dados da API.
- Evita a exposição direta das entidades.

**Security**
- Implementa autenticação e autorização com JWT.
- Protege os endpoints da aplicação.

**Exception**
- Centraliza o tratamento de erros e exceções da API.
```

## Melhorias Futuras

Algumas evoluções planejadas para o projeto:

- Implementação de testes de integração
- Paginação na listagem de tarefas
- Filtros por status das tarefas
- Recuperação de senha
- Controle de permissões por perfil de usuário
- Pipeline de CI/CD com GitHub Actions
- Cobertura de testes automatizada
- Deploy em ambiente cloud
- Monitoramento e observabilidade da aplicação
- Documentação mais detalhada dos endpoints
```
