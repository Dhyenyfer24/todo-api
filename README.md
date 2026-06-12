# Todo API

API REST para gerenciamento de tarefas com autenticação JWT.

## Tecnologias

- Java 17
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- JPA/Hibernate
- Maven

## Funcionalidades

- Cadastro de usuários
- Login com JWT
- Criar tarefas
- Listar tarefas do usuário autenticado
- Controle de status (TODO / DONE)
- Tratamento global de exceções

## Endpoints

POST /auth/register
POST /auth/login

GET /tasks
POST /tasks

## Segurança

A API utiliza JWT para autenticação.
