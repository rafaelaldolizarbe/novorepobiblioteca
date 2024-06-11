package thelibrary.api.biblioteca.dto.livro;

import thelibrary.api.biblioteca.entity.livro.Livro;

public record DadosConsultaLivro(
        String titulo,
        String autor,
        Integer anoPublicacao,
        String editora
) {
    public DadosConsultaLivro(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao(), livro.getEditora());
    }
}
