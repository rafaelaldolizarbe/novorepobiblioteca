package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.constraints.NotNull;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Contato;
import thelibrary.api.biblioteca.enums.GeneroLiterario;

import java.time.LocalDate;

public record AutorDetalhamentoDto(
    Long id,
    LocalDate dataNascimento,
    @NotNull
    String nome,
    String nacionalidade,
    GeneroLiterario generoLiterario,
    Contato contato
) {
    public AutorDetalhamentoDto(Autor autor) {
        this(autor.getId(),autor.getDataNascimento(),autor.getNome(), autor.getNacionalidade(), autor.getGeneroLiterario(), autor.getContato());
    }
}
