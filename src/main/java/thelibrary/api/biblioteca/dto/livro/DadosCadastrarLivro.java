package thelibrary.api.biblioteca.dto.livro;

import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

public record DadosCadastrarLivro(
        String titulo,
        DadosCadastroAutorLivro autor,
        Integer anoPublicacao,
        String editora,
        GeneroLiterario genero,
        String isbn



) {
}
