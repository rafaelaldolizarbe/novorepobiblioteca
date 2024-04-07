package thelibrary.api.biblioteca.dto.livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarLivro(
        @NotNull
        Long id,
        String titulo,
        String autor,
        Integer anoPublicacao,
        String editora,
        String isbn,
        String genero

) {
}
