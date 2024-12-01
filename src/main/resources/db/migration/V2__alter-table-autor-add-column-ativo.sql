ALTER TABLE autor ADD COLUMN ativo TINYINT;

UPDATE autor SET ativo = 1;