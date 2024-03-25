create table autor(

    id bigint not null auto_increment primary key,
    nome varchar(100) not null,
    data_nascimento date not null,
    nacionalidade varchar(100) not null,
    genero_literario varchar(100) not null,
    email varchar(100) not null unique,
    endereco_postal varchar(100) not null,
    telefone varchar(11) not null,
    numero varchar(20),
    preferencia_de_contato varchar(100),
    rede_social varchar(200),
    site varchar(100),
    disponibilidade_de_eventos boolean not null
);
