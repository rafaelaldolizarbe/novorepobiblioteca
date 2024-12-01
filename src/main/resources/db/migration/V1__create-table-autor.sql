CREATE TABLE autor (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    nacionalidade VARCHAR(100) NOT NULL,
    genero_literario VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco_postal VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    numero VARCHAR(20),
    preferencia_de_contato VARCHAR(100),
    rede_social VARCHAR(200),
    site VARCHAR(100),
    disponibilidade_de_eventos BOOLEAN NOT NULL
);
