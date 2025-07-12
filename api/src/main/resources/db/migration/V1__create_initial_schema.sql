-- criação da tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- criação da tabela de eventos
CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    date TIMESTAMP NOT NULL,
    place VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    capacity INT NOT NULL CHECK (capacity > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_events_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE
);

-- Criação de índices para melhorar performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_events_user_id ON events(user_id);