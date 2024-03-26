package thelibrary.api.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import thelibrary.api.biblioteca.dto.livro.DadosCadastrarLivro;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;


@Table(name = "livro")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private String editora;
    private GeneroLiterario genero;
    private String isbn;

    public Livro(DadosCadastrarLivro livro) {
        this.titulo = livro.titulo();
        this.autor = livro.autor();
        this.anoPublicacao = livro.anoPublicacao();
        this.editora = livro.editora();
        this.genero = livro.genero();
        this.isbn = livro.isbn();
    }
}
