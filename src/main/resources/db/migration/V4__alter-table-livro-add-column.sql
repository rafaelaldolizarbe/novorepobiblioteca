ALTER TABLE livro ADD COLUMN ativo TINYINT;

UPDATE livro SET ativo = 1;