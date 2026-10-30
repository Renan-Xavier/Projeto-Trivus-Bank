CREATE TABLE IF NOT EXISTS tb_usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE,
    saldo DECIMAL(15, 2) DEFAULT 0,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS tb_categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS tb_transacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA')),
    valor DECIMAL(15, 2) NOT NULL,
    descricao VARCHAR(255),
    data_transacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuarios(id),
    FOREIGN KEY (id_categoria) REFERENCES tb_categorias(id)
);

INSERT INTO tb_usuarios (nome, email, senha, cpf, saldo, ativo) 
SELECT 'Usuário Teste', 'usuario@trivus.com', '123456', '12345678900', 5000.00, TRUE
WHERE NOT EXISTS (SELECT 1 FROM tb_usuarios WHERE email = 'usuario@trivus.com');

INSERT INTO tb_categorias (nome, descricao, ativo)
SELECT 'Alimentação', 'Despesas com alimentação', TRUE
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE nome = 'Alimentação');

INSERT INTO tb_categorias (nome, descricao, ativo)
SELECT 'Transporte', 'Despesas com transporte', TRUE
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE nome = 'Transporte');

INSERT INTO tb_categorias (nome, descricao, ativo)
SELECT 'Salário', 'Entrada de salário', TRUE
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE nome = 'Salário');
