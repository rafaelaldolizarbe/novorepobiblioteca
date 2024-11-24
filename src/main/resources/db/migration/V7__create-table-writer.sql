
-- Criação da tabela de Escritor
CREATE TABLE writer (
    created_by INT,
    id INT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    last_modified_by INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL UNIQUE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    birth_date DATE,
    death_date DATE,
    description TEXT
);

-- Criação da tabela de relação entre Escritor e Livro
CREATE TABLE write (
    id_writer INT,
    id_book INT,
    PRIMARY KEY (id_writer, id_book),
    FOREIGN KEY (id_writer) REFERENCES writer(id),
    FOREIGN KEY (id_book) REFERENCES book(id)
);