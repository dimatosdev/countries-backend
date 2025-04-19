
# Countries API

O Projeto é uma aplicação back-end desenvolvida em [Spring Boot](https://projects.spring.io/spring-boot) como parte de um desafio para demonstrar a produtividade em construir APIs utilizando frameworks.
Se trata de uma API que fornece endpoints para **gestão de países**, permitindo operações como listar, pesquisar, salvar e excluir registros. A autenticação é feita via token, com permissões específicas para administradores.

___


## Funcionalidades

- 🔐 Autenticação com token
- 👤 Controle de acesso com privilégio de administrador
- 📄 Listar todos os países
- 🔍 Pesquisar países por nome
- 💾 Criar e atualizar países
- ❌ Excluir países
- 📘 Documentação automática com Swagger/OpenAPI

___
## Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2
- Swagger / Springdoc OpenAPI
- Maven
## Requisitos
- [Spring Boot 3.4.4](https://projects.spring.io/spring-boot);
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

___
## 📚 Documentação da API

Após subir a aplicação, acesse:

- Link:  
  👉 `http://localhost:8080/api`

- Swagger UI:  
  👉 `http://localhost:8080/swagger-ui.html`

- Documentação OpenAPI (YAML):  
  👉 `http://localhost:8080/v3/api-docs.yaml`

- Documentação OpenAPI (JSON):  
  👉 `http://localhost:8080/v3/api-docs`

---

#### Autenticação do usuário

```http
POST /usuario/autencicar/
```

| Parâmetro | Tipo     | Descrição                    |
|-----------|----------|------------------------------|
| `login`   | `string` | **Obrigatório**. Nome de login |
| `senha`   | `string` | **Obrigatório**. Senha do usuário |

**Retorna:** um objeto com o token e dados do usuário autenticado.

---

#### Renovar Token de Acesso

```http
GET /usuario/renovar-ticket
```

| Parâmetro | Tipo     | Descrição                         |
|-----------|----------|-----------------------------------|
| `token`   | `string` | **Obrigatório**. Token do usuário |

**Retorna:** true se reautenticou o token, false se não conseguiu reautenticar

---

#### Listar todos os países

```http
GET /pais/listar
```

| Parâmetro       | Tipo     | Descrição                                                          |
|-----------------|----------|--------------------------------------------------------------------|
| `Authorization` | `string` | **Obrigatório**. Token de autenticação no formato `Bearer <token>` |

**Retorna:** todos os países cadastrados em formato JSON.

---

#### Pesquisar países por nome, sigla ou gentílico

```http
GET /pais/pesquisar?filtro=nome
```

| Parâmetro       | Tipo     | Descrição                                                          |
|-----------------|----------|--------------------------------------------------------------------|
| `Authorization` | `string` | **Obrigatório**. Token de autenticação no formato `Bearer <token>` |
| `filtro`        | `string` | **Obrigatório**. Texto a ser pesquisado                            |

**Retorna:** os países que contêm o valor informado no nome, sigla ou gentílico.

---

#### Cadastrar ou atualizar um país

```http
POST /pais/salvar?token=SEU_TOKEN
```

| Parâmetro da URL | Tipo     | Descrição                              |
|------------------|----------|----------------------------------------|
| `token`          | `string` | **Obrigatório**. Token de usuário      |

**Body (JSON):**

```json
{
  "id": 1,
  "nome": "Brasil",
  "sigla": "BR",
  "gentilico": "brasileiro"
}
```

**Retorna:** o país salvo com sucesso.

---

#### Excluir um país

```http
DELETE /pais/excluir/{id}
```

| Parâmetro       | Tipo     | Descrição                                                          |
|-----------------|----------|--------------------------------------------------------------------|
| `Authorization` | `string` | **Obrigatório**. Token de autenticação no formato `Bearer <token>` |
| `id`            | `int`    | **Obrigatório**. ID do país a excluir                                    |

**Retorna:** status de sucesso ou erro.

___
## ⚙️ Como Rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/dimatosdev/countries-backend.git

# Acesse a pasta
cd countries-backend

# Compile e rode o projeto
./mvnw spring-boot:run
```

A API será executada em:  
📍 `http://localhost:8080`

___
## 📂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com.mngs.countries.countries_backend/
│   │       ├── config/
│   │       ├── controllers/
│   │       ├── dto/
│   │       ├── entities/
│   │       ├── repositories/
│   │       └── service/
│   └── resources/
│       ├── application.properties
│       ├── import.sql
│       └── ...
```

---
## ✉️ Contato

Se tiver dúvidas ou sugestões:

- 📧 dimatosdev@gmail.com
- 💼 [LinkedIn](https://www.linkedin.com/in/diegodematos)
