# 📁 PortfolioPro

Sistema para **gestão de portfólio de projetos** empresariais, construído com foco em **qualidade de código, arquitetura limpa, desempenho e boas práticas** de desenvolvimento corporativo Java.

---

## ✅ Objetivo

Gerenciar o ciclo de vida dos projetos de uma empresa, desde sua criação, análise de viabilidade até o encerramento, respeitando regras de negócio robustas e integrando um front-end baseado em JSP + Bootstrap.

---

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.1+**
- **Spring MVC / JPA / Data**
- **Jakarta JSP + JSTL 2.0**
- **PostgreSQL**
- **Flyway (migrations versionadas)**
- **JUnit 5 (com cobertura por camada)**
- **Arquitetura Hexagonal (Ports & Adapters)**
- **Maven Multi-módulo (domain, application, infra, web)**
- **Docker + Docker Compose**

---

## 🧱 Estrutura do Projeto

```bash
portfoliopro/
│
├── domain/        # Regras de negócio e modelos (entidades e ports)
├── infra/         # Adapters (repositórios JPA, configuração, Flyway)
├── web/           # Camada de apresentação (controllers, JSPs)
```

---

## 🧠 Arquitetura

O sistema adota **arquitetura hexagonal (Ports & Adapters)** com separação rigorosa das responsabilidades:

- **Domain:** modelos imutáveis, entidades ricas, validações e regras de negócio
- **Application:** casos de uso e orquestração dos serviços
- **Infra:** implementações concretas (repositórios, persistência, beans, Flyway)
- **Web:** controllers Spring MVC e views JSP responsivas com Bootstrap

---

## 📌 Requisitos de Negócio Atendidos

- ✅ CRUD completo de projetos
- ✅ Regras específicas para status e risco
- ✅ Associação de membros apenas se forem funcionários
- ✅ Proibição de exclusão com status sensível
- ✅ Cadastro de membros exclusivamente via WebService
- ✅ Validações de datas, orçamento, gerente e status
- ✅ Paginação eficiente usando `Pageable`
- ✅ Separação entre `ID` interno e identificador externo (CPF)

---

## 🧪 Testes Automatizados

O projeto cobre testes por camada:

- **Unitários (JUnit 5):**
    - Domain: validação de regras de negócio
    - Application: casos de uso com mocks
    - Infra: repositórios com banco real em memória
- **Cobertura dos critérios obrigatórios e edge cases**

---

## 🐳 Docker

Execute o ambiente completo com:

```bash
docker compose --profile app up --build
```

Inclui:
- PostgreSQL com usuários separados (app/flyway)
- Aplicação WAR rodando via Tomcat
- Migrations Flyway automáticas na inicialização

---

## 🔥 Destaques Técnicos para Avaliação

- Estrutura escalável e modular (multi-módulo com `pom.xml` limpo)
- Separação clara de responsabilidades
- Utilização eficiente de `Pageable`, `DTOs`, e `Mapper`
- JSP moderno com `jakarta.tags` e Bootstrap responsivo
- Regras complexas de negócio cobertas por testes e validações explícitas
- Código limpo, sem uso de Lombok, com **builders manuais, imutabilidade e logs relevantes**
- Integração contínua possível com pipeline e banco versionado
- Pronto para deploy em nuvem/container com `Dockerfile`

---

## ▶️ Como Rodar Localmente

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/portfoliopro.git
cd portfoliopro
```

2. Compile o projeto:

```bash
mvn clean install
```

3. Rode via Spring Boot (modo standalone):

```bash
cd web
mvn spring-boot:run
```

4. Rode via Docker (com Docker Compose):

```bash
docker compose --profile app up --build
```

Ou use o Tomcat externo com `.war`.

## ▶️ Como Acessar
``http://localhost:8080/projetos``

### Exemplo de request para criar uma pessoa

```bash
curl -X POST http://localhost:8080/api/pessoas   -H "Content-Type: application/json"   -d '{
    "nome": "João Silva",
    "cpf": "023.456.789-00",
    "dataNascimento": "1990-05-10",
    "funcionario": true,
    "gerente": true
  }'
```
---

## 👤 Autor

**Felipe Gomes Caldas**  
Desenvolvedor Fullstack Java | Arquiteto de soluções  
[LinkedIn](https://www.linkedin.com/in/felipe-g-caldas/) | [GitHub](https://github.com/zeldsc)

---

## ✅ Avaliador(a)

Este projeto foi estruturado visando clareza, manutenibilidade e demonstração prática de conhecimento técnico aprofundado.
