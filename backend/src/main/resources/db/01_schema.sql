CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS contato (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL CHECK (tipo IN ('TELEFONE', 'EMAIL')),
    valor VARCHAR(100) NOT NULL,
    observacao TEXT,
    cliente_cpf VARCHAR(14) REFERENCES cliente(cpf)
);