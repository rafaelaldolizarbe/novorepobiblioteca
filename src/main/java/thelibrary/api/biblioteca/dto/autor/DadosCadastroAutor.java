package thelibrary.api.biblioteca.dto.autor;

import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

import java.time.LocalDate;

public record DadosCadastroAutor(
        String nome,
        LocalDate dataNascimento,
        GeneroLiterario generoLiterario,
        String nacionalidade,
        DadosContato contato
) {
}
