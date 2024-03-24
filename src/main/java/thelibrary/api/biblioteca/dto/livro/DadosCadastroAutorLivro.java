package thelibrary.api.biblioteca.dto.livro;

import thelibrary.api.biblioteca.entity.GeneroLiterario;

import java.time.LocalDate;

public record DadosCadastroAutorLivro(
        String nome,
        LocalDate dataNascimento,
        GeneroLiterario generoLiterario,
        String nacionalidade
) {

}
