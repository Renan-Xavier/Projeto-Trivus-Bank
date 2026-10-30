# Trivus Bank — Ecossistema Fintech Educativo & Acessível

<p align="center">
  <img src="Assets/Images/logo-trivus.svg" alt="Logo Trivus Bank" width="160" />
</p>

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-REST_API-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-Frontend-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Oracle](https://img.shields.io/badge/Oracle_SQL-JDBC_%26_MER-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)

**Autor:** Renan Santa Rosa Xavier — RM566997

---

## Sobre o Projeto

O **Trivus Bank** é uma Fintech e banco digital educativo criado ao longo das fases do curso de Análise e Desenvolvimento de Sistemas (FIAP). Seu nome vem de **"Três Virtudes": Segurança, Simplicidade e Transparência**, com foco especial na inclusão digital de idosos e pessoas com dificuldade em aplicativos bancários tradicionais.

![Apresentação Fintech Trivus Bank](Assets/Images/fintech_slide_p01.png)

---

## Vitrine Visual da Plataforma (Landing Page & Experiência do Usuário)

| Problema: Complexidade para Idosos | Solução: App Simples e Acessível |
| :---: | :---: |
| ![Idoso com dificuldade no app](Assets/Images/idoso_chateado_com_app_do_banco.png) | ![Homem utilizando Trivus Bank](Assets/Images/homem%20segurando%20cel%20com%20app%20trivus%20bank.png) |

| Educação Financeira e Perfil | Assistente com Inteligência Artificial |
| :---: | :---: |
| ![Questionário de perfil](Assets/Images/uma_pessoa_respondendo_questionario_do_perfil.png) | ![IA auxiliando usuários](Assets/Images/ia_auxiliando_pessoas_no_banco.png) |

---

## Modelagem de Banco de Dados (MER Lógico e Físico)

| Modelo Lógico Relacional | Modelo Físico Oracle SQL |
| :---: | :---: |
| ![MER Lógico Trivus Bank](Assets/Images/MerTrivusBankFisicoLogicol.png) | ![MER Físico Trivus Bank](Assets/Images/MerTrivusBankFisico.png) |

---

## Arquitetura dos 4 Módulos do Repositório

```mermaid
flowchart TD
    Root["Ecossistema Trivus Bank"] --> M1["1. Landing Page Web\n(index.html + Bootstrap 5.3 + Dark Mode)"]
    Root --> M2["2. Domínio Java POO\n(Projeto Trivus Bank Java/)"]
    Root --> M3["3. Camada de Dados JDBC Oracle\n(Projeto Trivus Bank/)"]
    Root --> M4["4. Aplicação Full-Stack (Fase 7)\n(Trivus Bank Projeto Fase 7 - Cap14/)"]
    M4 --> B["Backend: Java 17 + Spring Boot"]
    M4 --> F["Frontend: React + Node.js"]
```

### 1. Landing Page Interativa ([`index.html`](index.html) e [`Assets/`](Assets))
- **Tecnologias:** HTML5, CSS3, Bootstrap 5.3, JavaScript (Dark Mode nativo) e vídeo de background.
- **Destaques de Acessibilidade:** Tipografia ampliada em 50%, alto contraste (`#00563F` / `#00cf87`), carrossel explicativo de 6 etapas e responsividade mobile.

### 2. Domínio Orientado a Objetos em Java ([`Projeto Trivus Bank Java/`](Projeto%20Trivus%20Bank%20Java))
- Implementação de herança, polimorfismo e encapsulamento para as entidades centrais da Fintech: `Conta`, `ContaCorrente`, `CartaoCredito`, `TransferenciaPix`, `UsuarioFintech` e `TesteFintech`.

### 3. Persistência e DAO com Oracle JDBC ([`Projeto Trivus Bank/`](Projeto%20Trivus%20Bank) e [`Sql proj trivus bank.sql`](Sql%20proj%20trivus%20bank.sql))
- Projeto Maven integrado ao driver oficial `ojdbc8` da Oracle para operações de banco de dados das tabelas financeiras.

### 4. Aplicação Full-Stack — Spring Boot + React ([`Trivus Bank Projeto Fase 7 - Cap14/`](Trivus%20Bank%20Projeto%20Fase%207%20-%20Cap14))
- **Backend (`backend/`):** API REST em Java 17 + Spring Boot gerenciando Usuários, Categorias e Transações financeiras (`http://localhost:8080`).
- **Frontend (`frontend/`):** Interface SPA em React consumindo a API em tempo real.

---

## Como Executar

### Landing Page
Abra o arquivo [`index.html`](index.html) diretamente no navegador.

### Aplicação Full-Stack (Fase 7)
```bash
# 1. Iniciar o Backend (Spring Boot)
cd "Trivus Bank Projeto Fase 7 - Cap14/backend"
mvn spring-boot:run

# 2. Iniciar o Frontend (React)
cd "Trivus Bank Projeto Fase 7 - Cap14/frontend"
npm install
npm start
```
