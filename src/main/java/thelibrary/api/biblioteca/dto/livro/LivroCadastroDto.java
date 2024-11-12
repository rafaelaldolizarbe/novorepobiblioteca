package thelibrary.api.biblioteca.dto.livro;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;
import thelibrary.api.biblioteca.enums.GeneroLiterario;

public record LivroCadastroDto(
        @NotNull
        String titulo,
        @NotNull
        String autor,
        @NotNull
        Integer anoPublicacao,
        @NotNull
        String editora,
        @NotNull
        GeneroLiterario genero,
        @ISBN
        String isbn



) {
}
