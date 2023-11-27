Create Database sistema_saude;
Drop Database sistema_saude;

Use sistema_saude;
CREATE TABLE IF NOT EXISTS medicos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    especialidade VARCHAR(255) DEFAULT NULL,
    login VARCHAR(255) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    UNIQUE KEY login_UNIQUE (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS pacientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    UNIQUE KEY login_UNIQUE (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS senhas (
    id INT PRIMARY KEY,
    chave_secreta VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES pacientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS valorespadroes (
    id INT PRIMARY KEY,
    descricao VARCHAR(255) DEFAULT NULL,
    limite_superior INT NOT NULL,
    limite_inferior INT NOT NULL,
    unidade VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS lipase (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uL VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `sistema_saude`.`valorespadroes`
(id, descricao, limite_superior, limite_inferior, unidade)
VALUES
(1, 'Normal', 60, 13, 'U/L');

select * from valorespadroes;
select * from Pacientes;