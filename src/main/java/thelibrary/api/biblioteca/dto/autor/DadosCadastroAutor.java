package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

import java.time.LocalDate;

public record DadosCadastroAutor(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        GeneroLiterario generoLiterario,
        @NotBlank
        String nacionalidade,
        @NotNull @Valid DadosContato contato
) {
}
