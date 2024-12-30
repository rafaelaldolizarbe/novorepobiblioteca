# Modelo Entidade Relação da Biblioteca

## 1 Entidade: Livro

- id_livro: identificador único do livro.
- titulo: título do livro.
- data_publicação: ano em que o livro foi publicado.
- isbn
- id_genero: chave extranjeira
- id_publisher: cjave extramjeira


## 1.1.0 Entidade: Autor (1:n)

- id_autor
- isbn
- data_nacimento

## 1.1.1 Entidade: Genero Literário (1:n)

- id_do_genero
- nome_do_genero

## 1.2.0 Tabela Associativa : Escreve (n:n)

- id_relacao
- id_livro
- id_autor


````mermaid
---
title: Entidade Livro e seu contexto (MER Normalizado)
---
erDiagram
    book ||--o{ write : "has writed for"
    writer ||--o{ write : "writes"
    literary_genre ||--o{ book: "has a"
    publisher ||--o{ book : "publish"
    
    literary_genre{
        int id PK
        string genre_name
        
    }
    write{
       
        int id_writer PK,FK
        int id_book PK,FK
        
    
    } 
    writer{
        int id PK
        string first_name
        string second_name
        date birth_date
        date death_date
        string description 
        
    }
    book {
        int id PK
        string title
        date publication_date
        bigint isbn
        int id_literary_genre FK
        int id_publisher FK
    }
    publisher{
        int id PK
        string publisher_name
    }
````

### Script SQL

````sql

-- Criação da tabela de Gênero Literário
CREATE TABLE literary_genre (
    id INT PRIMARY KEY,
    genre_name VARCHAR(100) NOT NULL
);

-- Criação da tabela de Escritor
CREATE TABLE writer (
    id INT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    second_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    death_date DATE,
    description TEXT
    
);

-- Criação da tabela de Editora
CREATE TABLE publisher (
    id INT PRIMARY KEY,
    publisher_name VARCHAR(100) NOT NULL
);

-- Criação da tabela de Livro com referência ao Gênero Literário
CREATE TABLE book (
    id INT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    publication_name DATE,
    isbn BIGINT UNIQUE,
    id_literary_genre INT,
    id_publisher INT,
    FOREIGN KEY (id_literary_genre) REFERENCES literary_genre(id),
    FOREIGN KEY (id_publisher) REFERENCES publisher(id)
);

-- Criação da tabela de relação entre Escritor e Livro
CREATE TABLE book_writer (
    id_writer INT,
    id_book INT,
    PRIMARY KEY (id_writer, id_book),
    FOREIGN KEY (id_writer) REFERENCES writer(id),
    FOREIGN KEY (id_book) REFERENCES book(id)
);

````

## 2 Entidade: Biblioteca

- id : identificador da biblioteca
- nome : nome da biblioteca
- id_endereco : endereço da biblioteca, no caso a chave extranjeira

## 2.1 Entidade Associativa: Estoque

- id_library
- id_book
- quantidade
## 2.2 Entidade : Endereço

- id
- cep
- rua
- numero
- numero_da_sala
- cidade


````mermaid
erDiagram
    book ||--o{ stock : has
    library ||--o{ stock : has
    library ||--|| address: has

    book {
        int id PK
        string title
        date publication_date
        bigint isbn
        int id_literary_genre FK
        int id_publisher FK
    }

    library{
        int id PK 
        string name
        int id_address FK

    }

    address{
        int id PK
        string cep
        string street
        int number
        int room_number
        string city
    }
    stock{
        int id_library PK,FK
        int id_book PK,FK
        int amount

    }


````

### Script SQL

````sql
-- Tabela Book
CREATE TABLE book (
    id INT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    publication_name DATE,
    isbn BIGINT UNIQUE,
    id_literary_genre INT,
    id_publisher INT,
    FOREIGN KEY (id_literary_genre) REFERENCES literary_genre(id),
    FOREIGN KEY (id_publisher) REFERENCES publisher(id)
);

-- Tabela Address
CREATE TABLE address (
    id INT PRIMARY KEY,
    cep VARCHAR(20) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    room_number INT,
    city VARCHAR(100) NOT NULL
);

-- Tabela Library
CREATE TABLE library (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    id_address INT,
    FOREIGN KEY (id_address) REFERENCES address(id) ON DELETE SET NULL
);

-- Tabela Stock
CREATE TABLE stock (
    id_library INT,
    id_book INT,
    amount INT DEFAULT 0 CHECK (amount >= 0),  -- Garante que a quantidade é não negativa
    PRIMARY KEY (id_library, id_book),
    FOREIGN KEY (id_library) REFERENCES library(id) ON DELETE CASCADE,
    FOREIGN KEY (id_book) REFERENCES book(id) ON DELETE CASCADE
);


````


