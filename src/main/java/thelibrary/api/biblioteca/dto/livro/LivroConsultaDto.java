package thelibrary.api.biblioteca.dto.livro;

import thelibrary.api.biblioteca.entity.Livro;

public record LivroConsultaDto(
        String titulo,
        String autor,
        Integer anoPublicacao,
        String editora
) {
    public LivroConsultaDto(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao(), livro.getEditora());
    }
}
