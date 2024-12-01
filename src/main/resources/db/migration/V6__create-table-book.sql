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
CREATE TABLE IF NOT EXISTS book (
    id INT NOT NULL AUTO_INCREMENT,
    created_by INT,
    last_modified_by INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    isbn VARCHAR(13),
    title VARCHAR(200) NOT NULL,
    publication_date DATE,
    literary_genre_id INT,
    publisher_id INT,
    active BOOLEAN DEFAULT TRUE,
    INDEX (literary_genre_id),
    INDEX (publisher_id),
    FOREIGN KEY (literary_genre_id) REFERENCES literary_genre(id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id),
    PRIMARY KEY (id)
);


