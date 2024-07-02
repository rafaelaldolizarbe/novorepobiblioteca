package thelibrary.api.biblioteca.dto.autor;

import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.enums.GeneroLiterario;

public record AutorConsultaDto(
    Long id,
    String nome,
    String nacionalidade,
    GeneroLiterario generoLiterario
) {
    public AutorConsultaDto(Autor autor) {
        this(autor.getId(),autor.getNome(), autor.getNacionalidade(), autor.getGeneroLiterario());
    }
}
