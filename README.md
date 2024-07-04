# CLI Project: Nutrition Management System

Este projeto foi desenvolvido para aprimorar habilidades em Java, OOP, Design Patterns, Logging e Banco de Dados, 
através de um sistema de linha de comando (CLI) para gerenciamento de informações nutricionais.

## Objetivo

O objetivo deste projeto é criar um sistema que permita aos usuários realizar operações básicas de gerenciamento de informações nutricionais. 
O usuário informa seus dados pessoais e seu objetivo (bulking ou cutting) e o aplicativo calcula a quantidade ideal de macronutrientes para sua dieta. 
Isso inclui o cadastro de usuários, além da criação, leitura, atualização e exclusão de dietas e históricos associadas a cada usuário.


## Funcionalidades

- **CRUD:** Permite registrar, atualizar, ler e deletar as informações dos usuários.
- **Gerenciamento de Macronutrientes:** Fornece a quantidade exata de macronutrientes necessários de acordo com os dados e objetivos de cada usuário.
- **Gerenciamento de Histórico:** Gera um histórico do usuário com o nível e o tipo de IMC (Índice de Massa Corporal) do usuário.
- **Armazenamento de informações:** Todas as informações geradas, como macronutrientes, histórico IMC e informações pessoais, são armazenadas no banco de dados e um arquivo.txt.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **MySQL:** Banco de dados utilizado para armazenar informações de usuários e dietas.
- **JDBC:** API para conectar e interagir com o banco de dados MySQL.
- **Lombok:** Biblioteca que reduz o código repetitivo em Java, gerando automaticamente métodos como getters, setters e construtores através de anotações.
- **Git:** Controle de versão para gerenciar o código-fonte do projeto.
- **Maven:** Gerenciamento de dependências para construir e gerenciar o projeto Java.

## Como Executar

Para executar o projeto localmente, siga estas etapas:

1. Clone este repositório.
2. Configure um banco de dados MySQL localmente, execute o arquivo `setup.sql` localizado em `resources/setup.sql`.
3. Crie e configure as propriedades de conexão ao banco de dados no arquivo `main/resources/config.properties`.
```
url = <url_local>
password = <password>
user = <username>
```
4. Execute a classe principal `Run` para iniciar a aplicação.

