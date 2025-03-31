INSERT INTO cliente (cpf, nome, data_nascimento, endereco)
VALUES
    ('980.896.000-69', 'João Silva', '1990-05-15', 'Rua A, 123'),
    ('498.224.760-94', 'Maria Souza', '1985-10-20', 'Av B, 456')
ON CONFLICT (cpf) DO NOTHING;

INSERT INTO contato (tipo, valor, observacao, cliente_cpf)
VALUES
    ('TELEFONE', '(11) 9999-8888', 'Celular principal', '980.896.000-69'),
    ('EMAIL', 'joao@email.com', NULL, '980.896.000-69'),
    ('TELEFONE', '(21) 7777-6666', 'Recados', '498.224.760-94')
ON CONFLICT (id) DO NOTHING;