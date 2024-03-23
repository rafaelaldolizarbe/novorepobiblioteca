package thelibrary.api.biblioteca.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

import javax.persistence.*;

@Table(name = "livro")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    @Enumerated
    private GeneroLiterario genero;
    @ISBN
    private String isbn;

}
