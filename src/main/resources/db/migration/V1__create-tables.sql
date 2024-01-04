CREATE TABLE Status (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        descricao VARCHAR(50) NOT NULL,
                        criado_em DATETIME NOT NULL
);

CREATE TABLE Generos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(100) NOT NULL,
                         criado_em DATETIME NOT NULL
);

CREATE TABLE Usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          hash_senha VARCHAR(255) NOT NULL,
                          genero VARCHAR(100),
                          criado_em DATETIME NOT NULL
);

CREATE TABLE Livros (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        autor VARCHAR(255) NOT NULL,
                        editora VARCHAR(255),
                        ano_lancamento VARCHAR(255) NOT NULL,
                        criado_em DATETIME NOT NULL,
                        genero_id BIGINT,
                        status_id BIGINT,
                        FOREIGN KEY (genero_id) REFERENCES Generos(id),
                        FOREIGN KEY (status_id) REFERENCES Status(id)
);