package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import thelibrary.api.biblioteca.enums.GeneroLiterario;

import java.time.LocalDate;

@Builder
public record AutorCadastroDto(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        GeneroLiterario generoLiterario,
        @NotBlank
        String nacionalidade,
        @NotNull @Valid AutorContatoDto contato
) {
}
