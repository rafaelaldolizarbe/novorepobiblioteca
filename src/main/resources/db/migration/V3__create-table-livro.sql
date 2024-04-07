create table livro (

    id bigint not null auto_increment primary key,
    titulo varchar(100) not null,
    autor varchar(100) not null,
    ano_publicacao int not null,
    editora varchar(100) not null,
    genero varchar(100) not null,
    isbn varchar(100) not null


);