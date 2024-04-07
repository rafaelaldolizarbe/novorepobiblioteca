# Primeira Parte do Curso de Spring Boot realizado na Alura Concluído

Esta branch engloba além do projeto de biblioteca baseado no curso de spring boot da alura ,a implementação de um banco de dados MySQL com o docker focando principalmente no recurso volume do docker para posteriormente implementar em uma instância de computação em núvem. Isso garante que quaisquer cargas realizadas nas etapas de testes podem ou não persistir em um ambiente de produção. 

# Projeto Biblioteca

Este projeto está passando por uma atualização é baseado no exercício que foi publicado anteriormente.

Neste contexto a abordagem visa a utilização de migrations para persistência de dados e Records para facilitar a implementação e uso de DTOs

Como Banco de dados temos o MySQL como banco de persistência padrão e o h2 como banco de teste. 

## Uso de pageable

Neste contexto de desenvolvimento é possível  encontrar recursos de paginação . O que facilita bastante o lado do desenvolvimento front-end.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- MySQL
- Flyway
- JUnit
- H2

## Docker

Para subir o banco de dados MySQL, você pode configurar o container isolado utilizando um dockerfile seguindo os seguintes parâmetros. 

```dockerfile
FROM mysql

ENV MYSQL_ROOT_PASSWORD=[senha_do_banco] \ 
    MYSQL_DATABASE=[nome_do_banco] 

    
EXPOSE 3306
```
Crie a imagem com o comando abaixo:

```shell
docker build -t [nome_da_imagem] .
```


Com a imagem criada é possível instanciar o banco de dados com o comando abaixo:

```shell
docker run --name [nome_do_container] -v [caminho_do_host/nome_do_volume]:/var/lib/mysql --rm -e MYSQL_ROOT_PASSWORD=[senha_do_banco] -p 3306:3306 -d [nome_da_imagem]
```

Importante ressaltar que o volume apesar de ser opcional é importante para persistir os dados do banco de dados independente do container ser excluido. Isso pode também garantir a possibilidade de realizar o backup dos dados e posteriormente reutilizar em outro ambiente. Tal como uma EC2 na AWS.    

Outra consideração que deve-se deixar clara é o uso do `--rm` que garante que o container seja excluido após a finalização do processo. Muito útil para realização de testes.