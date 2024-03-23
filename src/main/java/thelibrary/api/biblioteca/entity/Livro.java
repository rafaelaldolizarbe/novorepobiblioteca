package thelibrary.api.biblioteca.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String titulo;

    private Autor autor;
    @NotNull
    private Integer anoPublicacao;
    private String editora;
    private GeneroLiterario genero;
    @ISBN
    private String isbn;

}
