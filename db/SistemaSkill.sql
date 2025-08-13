-- Criação das sequences
CREATE SEQUENCE seq_usuario_id START 1;
CREATE SEQUENCE seq_skill_id START 1;
CREATE SEQUENCE seq_usuario_skill_id START 1;

-- Tabela de usuários
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_usuario_id'),
    login VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    status VARCHAR(20),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultimo_acesso TIMESTAMP
);

-- Tabela de skills
CREATE TABLE skill (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_skill_id'),
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    imagem_url TEXT
);

-- Tabela associativa entre usuário e skill
CREATE TABLE usuario_skill (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_usuario_skill_id'),
    usuario_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    level INTEGER NOT NULL CHECK (level >= 1 AND level <= 10),
    
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_skill FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE,
    CONSTRAINT unique_usuario_skill UNIQUE (usuario_id, skill_id)
);
