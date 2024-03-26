package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAutor(
    @NotNull
    Long id,
    String nome,
    String nacionalidade
) {
}
