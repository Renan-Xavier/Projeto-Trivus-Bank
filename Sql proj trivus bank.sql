CREATE TABLE Usuario (
    usuario_id NUMBER PRIMARY KEY,
    nome_completo VARCHAR2(100) NOT NULL,
    cpf VARCHAR2(14) UNIQUE NOT NULL,
    senha VARCHAR2(50) NOT NULL,
    saldo_atual NUMBER(10, 2) DEFAULT 0,
    saldo_cofrinho NUMBER(10, 2) DEFAULT 0,
    perfil_conhecimento VARCHAR2(50)
);

CREATE TABLE ContaBancaria (
    conta_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    numero_agencia VARCHAR2(10) NOT NULL,
    numero_conta VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_conta_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Documento (
    documento_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    tipo_documento VARCHAR2(50),
    ano_referencia NUMBER(4),
    link_arquivo VARCHAR2(255),
    CONSTRAINT fk_documento_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE CartaoCredito (
    cartao_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    dia_vencimento NUMBER(2),
    CONSTRAINT fk_cartao_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Fatura (
    fatura_id NUMBER PRIMARY KEY,
    cartao_id NUMBER NOT NULL,
    mes_referencia NUMBER(2),
    ano_referencia NUMBER(4),
    valor_total NUMBER(10, 2),
    data_vencimento DATE,
    status VARCHAR2(20),
    CONSTRAINT fk_fatura_cartao FOREIGN KEY (cartao_id) REFERENCES CartaoCredito(cartao_id)
);

CREATE TABLE TransacaoCartao (
    transacao_id NUMBER PRIMARY KEY,
    fatura_id NUMBER NOT NULL,
    descricao VARCHAR2(100),
    data_transacao DATE,
    valor NUMBER(10, 2),
    CONSTRAINT fk_transacao_fatura FOREIGN KEY (fatura_id) REFERENCES Fatura(fatura_id)
);

CREATE TABLE Receita (
    receita_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    ds_receita VARCHAR2(100) NOT NULL,
    vl_receita NUMBER(10, 2) NOT NULL,
    dt_receita DATE NOT NULL,
    CONSTRAINT fk_receita_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Despesa (
    despesa_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    ds_despesa VARCHAR2(100) NOT NULL,
    vl_despesa NUMBER(10, 2) NOT NULL,
    dt_despesa DATE NOT NULL,
    CONSTRAINT fk_despesa_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Investimento (
    investimento_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    ds_investimento VARCHAR2(100) NOT NULL,
    vl_investimento NUMBER(10, 2) NOT NULL,
    dt_investimento DATE NOT NULL,
    CONSTRAINT fk_investimento_usuario FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);