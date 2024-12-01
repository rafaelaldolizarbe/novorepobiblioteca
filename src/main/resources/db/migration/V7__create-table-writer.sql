
-- Criação da tabela de Escritor
CREATE TABLE writer (
    created_by INT,
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    last_modified_by INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL UNIQUE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    birth_date DATE,
    death_date DATE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    description TEXT
);

-- Criação da tabela de relação entre Escritor e Livro
CREATE TABLE book_writer (
    writer_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY (writer_id, book_id),
    FOREIGN KEY (writer_id) REFERENCES writer(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);


