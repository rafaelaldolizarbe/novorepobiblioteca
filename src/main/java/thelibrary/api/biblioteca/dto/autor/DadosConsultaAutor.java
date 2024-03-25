package thelibrary.api.biblioteca.dto.autor;

import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

public record DadosConsultaAutor(
    String nome,
    String nacionalidade,
    GeneroLiterario generoLiterario
) {
    public DadosConsultaAutor (Autor autor) {
        this(autor.getNome(), autor.getNacionalidade(), autor.getGeneroLiterario());
    }
}
