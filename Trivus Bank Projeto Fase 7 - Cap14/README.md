Trivus Bank - Fintech Application


Entidades Principais

1. Usuários - Gestão de contas de usuários com saldo
2. Categorias - Categorização de transações
3. Transações - Registro de entradas e saídas financeiras

Instruções de Inicialização

Pré-requisitos

- Backend:
  - Java 17+
  - Maven 3.6+

- Frontend:
  - Node.js 14+
  - npm 6+

Iniciar o Backend

cd backend

mvn spring-boot:run

A API estará disponível em: http://localhost:8080

Iniciar o Frontend

Em outro terminal:

cd frontend

npm install

npm start

A aplicação estará disponível em: http://localhost:3000


A aplicação estará disponível em: http://localhost:3000

Credenciais de Teste

Use estas credenciais para fazer login na aplicação:

 Email : usuario@trivus.com 
 Senha : 123456 

Estrutura do Projeto

Trivus Bank/
├── backend/                    API REST (Java/Spring Boot)
│   ├── src/main/java/          Código fonte
│   ├── src/main/resources/     Configurações
│   └── pom.xml
│
├── frontend/                   Interface Web (ReactJS)
│   ├── src/                    Código fonte
│   ├── public/                 Arquivos estáticos
│   └── package.json
│
└── README.md                   Este arquivo

Funcionalidades

Backend (REST API)

- Usuários - CRUD completo com validação
- Categorias - Gerenciamento de categorias
- Transações - Controle de entrada/saída com validação de saldo

Frontend (React)

- Login - Autenticação de usuário
- Dashboard - Visualização de saldo e transações
- CRUD Completo - Gerenciar usuários, categorias e transações
- Interface Responsiva - Adapta-se a qualquer tamanho de tela

Entidades do Sistema

Usuário
- Nome, Email (único), Senha, CPF (único)
- Saldo em tempo real
- Data de criação e atualização
- Status ativo/inativo

Categoria
- Nome (único), Descrição
- Data de criação e atualização
- Status ativo/inativo

Transação
- Tipo (ENTRADA/SAIDA), Valor, Descrição
- Relação com Usuário e Categoria
- Data da transação
- Status ativo/inativo

Regras de Negócio

1. Email único por usuário
2. CPF único por usuário
3. Nome único por categoria
4. Não permite saída superior ao saldo disponível
5. Saldo atualizado automaticamente com transações
6. Soft delete (marca como inativo, não remove)
7. Histórico de datas em todas as entidades

Tecnologias

Backend:
- Java 17, Spring Boot 3.1.0, Spring Data JPA, H2 Database

Frontend:
- ReactJS 18.2.0, React Router 6.14.0, Axios 1.4.0, CSS3

Endpoints da API

Usuários
- `GET /api/usuarios` - Listar todos
- `GET /api/usuarios/{id}` - Obter por ID
- `POST /api/usuarios` - Criar novo
- `PUT /api/usuarios/{id}` - Atualizar
- `DELETE /api/usuarios/{id}` - Deletar

Categorias
- `GET /api/categorias` - Listar todos
- `POST /api/categorias` - Criar novo
- `PUT /api/categorias/{id}` - Atualizar
- `DELETE /api/categorias/{id}` - Deletar

Transações
- `GET /api/transacoes` - Listar todos
- `GET /api/transacoes/usuario/{usuarioId}` - Por usuário
- `POST /api/transacoes` - Criar novo
- `PUT /api/transacoes/{id}` - Atualizar
- `DELETE /api/transacoes/{id}` - Deletar

Configuração Oracle FIAP

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=LOGIN        ← COLOCA SEU USUÁRIO AQUI
spring.datasource.password=SENHA          ← COLOCA SUA SENHA AQUI
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

Troubleshooting

Backend não inicia:
- Verifique se Java 11+ está instalado: `java -version`
- Verifique se Maven está instalado: `mvn -v`
- Limpe cache: `mvn clean`

Frontend não conecta ao backend:
- Certifique-se que o backend está rodando em `http://localhost:8080`
- Verifique o console do navegador para erros CORS

Erro ao fazer login:
- Use as credenciais corretas: `usuario@trivus.com` / `123456`
- Verifique se o backend está respondendo

Licença

Este projeto foi desenvolvido como atividade acadêmica da FIAP.



Exemplo de Fluxo Completo

1. Login - Usuário autentica com email/senha
2. Dashboard - Visualiza saldo e últimas transações
3. Criar Categoria - Adiciona nova categoria
4. Criar Transação - Registra uma transação na categoria
5. Atualizar Transação - Modifica valor ou descrição
6. Consultar Saldo - Dashboard atualiza automaticamente
7. Deletar Transação - Remove a transação, reverte saldo

Licença

Este projeto foi desenvolvido como atividade acadêmica.

Autor

Aluno: [Seu Nome]
Disciplina: Projeto - Fase 7
Instituição: FIAP

Versão: 1.0.0
Data: Maio 2024
