package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.constraints.NotNull;

public record AutorAtualizacaoDto(
    @NotNull
    Long id,
    String nome,
    String nacionalidade
) {
}
