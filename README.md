# CLI Project: Nutrition Management System

Este é um projeto de linha de comando (CLI) desenvolvido em Java para gerenciar informações nutricionais pessoais, aplicando conceito como CRUD, padrões de design, e acesso a banco de dados.

## Objetivo

O objetivo deste projeto é criar um sistema que permita aos usuários realizar operações básicas de gerenciamento de informações nutricionais, incluindo cadastro de usuário, criação, leitura, atualização e exclusão de dietas associadas a cada usuário.

## Funcionalidades

- **Cadastro de Usuário:** Permite registrar novos usuários com informações pessoais e objetivos nutricionais.
- **Gerenciamento de Dietas:** Criação, leitura, atualização e exclusão de dietas personalizadas para cada usuário.
- **Persistência em Banco de Dados:** Utiliza JDBC para conectar e manipular dados em um banco de dados MySQL.
- **Padrões de Design:** Aplica padrões como Singleton, DAO (Data Access Object), e MVC (Model-View-Controller) para estruturar o projeto.
- **Logging: Integração:** com java.util.logging para registro de eventos e erros.
  
## Estrutura do Projeto

- **dao Pacote:** Contém classes DAO para manipulação de dados:
- **UserDAO:** Manipula dados de usuários na tabela user.
- **DietDAO:** Manipula dados de dietas na tabela diet.
- **model Package:** Define as entidades de domínio como User, Diet, Gender, Objective, e ActivityLevel.
- **factory Package:** Fornece a classe ConnectionFactory para gerenciar a conexão com o banco de dados de forma Singleton.

### CLI (Interface de Linha de Comando)

- `UserInterface` que permite aos usuários interagir com o sistema por menus e comandos de texto.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **MySQL:** Banco de dados utilizado para armazenar informações de usuários e dietas.
- **JDBC:** API para conectar e interagir com o banco de dados MySQL.
- **Git:** Controle de versão para gerenciar o código-fonte do projeto.
- **Maven:** Gerenciamento de dependências para construir e gerenciar o projeto Java.

## Como Executar

Para executar o projeto localmente, siga estas etapas:

1. Clone este repositório.
2. Configure um banco de dados MySQL local seguindo as informações na classe DAO.
3. Configure as propriedades de conexão ao banco de dados no arquivo `config.properties`.
4. Compile o projeto utilizando Maven.
5. Execute a classe principal `Run` para iniciar a aplicação.

