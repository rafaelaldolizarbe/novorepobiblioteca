-- Criação da tabela de Editora
CREATE TABLE publisher (
    created_by INT,
    id INT NOT NULL AUTO_INCREMENT,
    last_modified_by INT,
    create_date TIMESTAMP NOT NULL UNIQUE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    publisher_name VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

-- Criação da tabela de Gênero Literário

CREATE TABLE literary_genre (
    created_by INT,
    id INT NOT NULL AUTO_INCREMENT,
    last_modified_by INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL UNIQUE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    genre_name VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);


-- Alteração da tabela de Livro
ALTER TABLE book
    ADD COLUMN title VARCHAR(200) NOT NULL,
    ADD COLUMN publication_date DATE,
    ADD COLUMN literary_genre_id INT,
    ADD COLUMN publisher_id INT,
    DROP COLUMN author,
    ADD FOREIGN KEY (literary_genre_id) REFERENCES literary_genre(id),
    ADD FOREIGN KEY (publisher_id) REFERENCES publisher(id),
    MODIFY COLUMN create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL UNIQUE;
