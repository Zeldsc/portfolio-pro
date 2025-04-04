-- Criação da tabela de pessoas
CREATE TABLE pessoa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    datanascimento DATE,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    funcionario BOOLEAN NOT NULL,
    gerente BOOLEAN NOT NULL
);

CREATE INDEX idx_pessoa_nome ON pessoa(nome);
CREATE INDEX idx_pessoa_funcionario ON pessoa(funcionario);
CREATE INDEX idx_pessoa_gerente ON pessoa(gerente);

-- Tabela de projetos (sem particionamento)
CREATE TABLE projeto (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE NOT NULL,
    data_previsao_fim DATE,
    data_fim DATE,
    descricao VARCHAR(5000),
    status VARCHAR(45) NOT NULL,
    orcamento FLOAT NOT NULL CHECK (orcamento >= 0),
    risco VARCHAR(45),
    idgerente BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente) REFERENCES pessoa(id)
);

CREATE INDEX idx_projeto_nome ON projeto(nome);
CREATE INDEX idx_projeto_status ON projeto(status);
CREATE INDEX idx_projeto_gerente ON projeto(idgerente);

-- Tabela de associação projeto-membros
CREATE TABLE projeto_membros (
    projeto_id BIGINT NOT NULL,
    pessoa_id BIGINT NOT NULL,
    PRIMARY KEY (projeto_id, pessoa_id),
    CONSTRAINT fk_projeto FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE,
    CONSTRAINT fk_membro FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);

CREATE INDEX idx_membro_projeto ON projeto_membros(pessoa_id);
CREATE INDEX idx_projeto_membro ON projeto_membros(projeto_id);
