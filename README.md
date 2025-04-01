SISTEMA DE GESTÃO DE CLIENTES E CONTATOS

*Visão Geral
Sistema full-stack para gerenciamento de clientes e seus contatos (telefones/emails), com:
- Backend em Spring Boot (Java)
- Frontend em React.js
- Banco de dados PostgreSQL

ESTRUTURA DO PROJETO

Backend (Spring Boot - Porta 8080)

Endpoints:
- GET /cliente/ → Lista todos os clientes
- GET /cliente/buscaNome/?nome={nome} → Lista todos os clientes filtrados por um nome
- GET /cliente/buscaCpf/?cpf={cpf} → Seleciona um cliente pelo seu CPF
- POST /cliente/ → Cadastra um cliente
- PUT /cliente/ → Atualiza um cliente
- DELETE /cliente/?cpf={cpf} → Deleta um cliente e todos os seus contatos
- GET /contato/buscaCpf/?cliente_cpf={cpf} → Lista todos os contatos de um cliente
- POST /contato/ → Cadastra um contato para um cliente especifico
- PUT /contato/ → Atualiza um contato de um cliente especifico
- DELETE /contato/?id={id} → Deleta um contato especifico

Configuração do Banco:
Schema SQL: Cria automaticamente as tabelas (schema.sql).
Dados Iniciais: Popula clientes e contatos (data.sql).
Diretório dos arquivos: /backend/src/main/resources/db

DEPENDÊNCIAS:
Backend: Spring Boot DevTools, Spring Data JPA, Spring Web, PostgreSQL Driver, Hibernate Core e Hibernate Validator.
Frontend: Node, React, React-Dom, React-Scripts, Typescript, React-Router-Dom e Axios.

Observações:
O backend usa Java 21 e Spring Boot 3.4.4 com PostgreSQL.
O frontend usa React 19 com TypeScript e React Router.

CONFIGURAÇÃO DO AMBIENTE DE TESTES:
Pré-requisitos:
- Docker e Docker-Compose (Recomendado)
ou
- Java 21+
- Node.js 21+
- PostgreSQL 17+
- Maven

EXECUÇÃO COM DOCKER COMPOSE (RECOMENDADO):
1. Execute o docker compose no diretório raiz do projeto:
   docker-compose up --build
3. Acesse a aplicação:
   Backend: http://localhost:8080
   Frontend: http://localhost:3000

EXECUÇÃO EM AMBIENTE LOCAL (NÃO RECOMENDADO):
1. Configure o banco de dados em `/backend/src/main/resources/application.properties`:
   spring.datasource.url=jdbc:postgresql://localhost(SEU HOST):5432(SUA PORTA)/clientes_db(SEU BANCO DE DADOS)
   spring.datasource.username=postgres(SEU USUARIO)
   spring.datasource.password=postgres(SUA SENHA)

2. Instale as dependências e executo o backend:
   cd backend
   mvn install
   mvn spring-boot:run

3. Instale as dependências e executo o frontend:
   cd frontend
   npm install
   npm start

CONTATO
Desenvolvedor - ailsonpra25@gmail.com




