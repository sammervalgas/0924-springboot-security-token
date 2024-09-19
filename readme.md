# Spring Boot JWT Authentication API

Este projeto implementa um sistema de autenticação com **JWT (JSON Web Token)** em uma aplicação **Spring Boot**, oferecendo diferentes níveis de autorização. O sistema de segurança é construído com base no **Spring Security** e gerencia usuários e permissões armazenados em um banco de dados **MySQL**. Além disso, o projeto inclui configuração para um **Docker Compose** com containers de MySQL e Adminer.

## Funcionalidades

- Autenticação e autorização baseadas em JWT.
- Gerenciamento de permissões (roles) como `USER` e `ADMIN`.
- Banco de dados MySQL para armazenamento de usuários e roles.
- Criptografia de senhas usando **BCrypt**.
- Suporte a **Refresh Token** para renovação do JWT.
- Configuração Docker com MySQL e Adminer para gerenciamento do banco de dados.

## Estrutura do Projeto

- **UserEntity**: Classe que representa o usuário no banco de dados, contendo campos como `username`, `password`, `roles` e `enabled`.
- **JwtRequestFilter**: Filtro responsável por interceptar requisições e validar o token JWT.
- **SecurityConfig**: Arquivo de configuração que define como os endpoints são protegidos e configurados com base nos perfis de segurança.
- **JwtUtil**: Utilitário para gerar e validar tokens JWT.
- **CustomDetailsService**: Implementação do serviço que carrega usuários e suas permissões a partir do banco de dados.
- **Dataloader**: Inicializa dados de usuarios no banco de dados.

## Tecnologias Usadas

- **Spring Boot**: Framework principal para construção da API.
- **Spring Security**: Para autenticação e autorização dos endpoints.
- **JWT**: Para autenticar e autorizar usuários por meio de tokens.
- **BCrypt**: Para criptografar e validar as senhas dos usuários.
- **MySQL**: Banco de dados relacional usado para armazenar informações de usuários e permissões.
- **Docker**: Utilizado para rodar os containers do MySQL e Adminer.

## Passo a Passo de Configuração

### 1. Configuração do Banco de Dados MySQL

O projeto utiliza o MySQL para armazenar os dados de autenticação e autorização. Usando o arquivo `docker-compose.yml`, você pode configurar e executar o banco de dados com um simples comando.

### 2. Criptografia de Senhas

As senhas dos usuários são armazenadas de forma segura no banco de dados, utilizando o algoritmo **BCrypt**. Ao criar novos usuários, as senhas são automaticamente criptografadas antes de serem salvas.

### 3. Configuração do JWT

O JWT é usado para autenticar e autorizar as requisições. Após uma autenticação bem-sucedida, o servidor gera um token JWT que deve ser incluído nas requisições subsequentes para acessar os endpoints protegidos.

### 4. Autorização com Roles

As permissões dos usuários são definidas por suas roles, como `USER` ou `ADMIN`. O acesso aos diferentes endpoints é restrito com base nessas roles. Usuários com role `ADMIN` podem acessar recursos administrativos, enquanto os usuários com role `USER` têm acesso limitado.

### 5. Implementação do Refresh Token

O projeto também suporta o uso de **refresh tokens** para renovar tokens de acesso sem a necessidade de autenticar novamente. Quando o token JWT de acesso expira, o refresh token pode ser utilizado para obter um novo token válido.

### 6. Exemplo de Endpoints

- **Login**: O endpoint de login recebe as credenciais do usuário e retorna um token JWT se as credenciais forem válidas.
- **Endpoints Protegidos**: Os endpoints são protegidos com base nas roles dos usuários. Por exemplo, usuários com role `ADMIN` podem acessar endpoints administrativos, enquanto os usuários com role `USER` têm acesso a outros recursos.

### 7. Autenticação com JWT

O fluxo de autenticação consiste em:
1. O usuário fornece suas credenciais (nome de usuário e senha).
2. Se as credenciais forem válidas, o servidor gera um token JWT.
3. O cliente armazena o token e o envia nas requisições subsequentes para acessar os recursos protegidos.

### 8. Configuração do Docker

Para rodar o ambiente completo, incluindo o banco de dados MySQL e o Adminer (ferramenta de gerenciamento de banco de dados), o projeto inclui um arquivo `docker-compose.yml`. Com ele, você pode iniciar ambos os serviços facilmente.

### 9. Configuração do Ambiente

As configurações, como detalhes do banco de dados e a chave secreta usada para gerar os tokens JWT, estão localizadas no arquivo de configuração do projeto. Essas configurações podem ser personalizadas de acordo com o ambiente.

## Como Executar o Projeto

1. **Clonar o Repositório**:
    - Baixe o projeto usando `git clone` para o seu ambiente local.

2. **Rodar o Docker Compose**:
    - Execute o comando `docker-compose up` para iniciar os containers do MySQL e Adminer.

3. **Rodar a Aplicação Spring Boot**:
    - Use o comando do Maven para rodar a aplicação Spring Boot.

4. **Testar a Autenticação**:
    - Utilize ferramentas como Postman ou cURL para enviar requisições aos endpoints protegidos.

## Principais Dependências Utilizadas

- **Spring Boot Starter Security**: Gerencia a segurança e autenticação da aplicação.
- **Spring Boot Starter Web**: Usado para criar a API REST.
- **Spring Boot Starter Data JPA**: Prove integração com o banco de dados MySQL usando JPA/Hibernate.
- **jjwt**: Biblioteca utilizada para gerar e validar tokens JWT.
- **BCrypt**: Utilizado para criptografar senhas.
- **Docker**: Para gerenciar os containers da aplicação (MySQL e Adminer).

---

Este documento resume as principais configurações e a arquitetura de segurança do projeto, incluindo o uso de JWT e roles de usuário, além de explicar como configurar o ambiente e executar a aplicação.
