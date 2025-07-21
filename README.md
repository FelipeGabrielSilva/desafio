# Desafio Técnico - API de Gerenciamento

Este repositório contém o código-fonte do backend para o desafio técnico. A aplicação foi desenvolvida em Java com o framework Spring Boot e tem como objetivo fornecer uma API RESTful completa.

## As principais tecnologias e ferramentas escolhidas para este projeto são:
* **Linguagem Backend:** Java 17
* **Framework Backend:** Spring Boot
* **Banco de Dados:** Supabase (PostgreSQL)
* **Controle de Versão:** Git
* **Frontend:** React (Disponível em: [desafio-frontend](https://github.com/FelipeGabrielSilva/desafio-frontend))

## Funcionalidades e Dependências
* `spring-boot-starter-web`: Para criar a API REST, lidar com requisições HTTP e utilizar o servidor Tomcat embutido.
* `spring-boot-starter-data-jpa`: Para a camada de persistência de dados com JPA e Hibernate, facilitando a interação com o banco de dados.
* `postgresql`: Driver JDBC para conectar e interagir com o banco de dados PostgreSQL do Supabase.
* `spring-boot-starter-validation`: Para validar dados de entrada, usando anotações.
* `lombok`: Para reduzir código repetitivo nas classes Java, como getters, setters e construtores.
* `spring-boot-devtools`: Ferramentas para o ambiente de desenvolvimento.

Para o desenvolvimento do projeto, foi aplicada a metodologia Object Calisthenics.

## ⚡ Como Executar o Projeto

Siga os passos abaixo para executar o backend em sua máquina local.

### Pré-requisitos

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Git](https://git-scm.com/downloads)
* Uma instância do Supabase (ou qualquer PostgreSQL) para conectar a aplicação.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)
    cd SEU_REPOSITORIO
    ```

2.  **Configure o Banco de Dados:**
    Renomeie ou crie o arquivo `application.properties` dentro de `src/main/resources/` e adicione as credenciais do seu banco de dados Supabase/PostgreSQL:
    ```properties
    # Configuração do Banco de Dados (PostgreSQL/Supabase)
    spring.datasource.url=jdbc:postgresql://SEU_HOST_SUPABASE:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=SUA_SENHA_DO_BANCO

    # Configuração do Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.show-sql=true
    ```

3.  **Execute a aplicação:**
    Use o Maven para iniciar o servidor.
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

