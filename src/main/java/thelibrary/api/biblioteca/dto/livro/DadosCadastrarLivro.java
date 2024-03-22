package thelibrary.api.biblioteca.dto.livro;

import thelibrary.api.biblioteca.dto.autor.DadosCadastroAutor;

public record DadosCadastrarLivro(
        String titulo,
        DadosCadastroAutorLivro autor,
        Integer anoPublicacao,
        String editora,
        String genero



) {
}
