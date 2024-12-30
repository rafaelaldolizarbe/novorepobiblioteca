package thelibrary.api.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thelibrary.api.biblioteca.dto.livro.LivroAtualizacaoDto;
import thelibrary.api.biblioteca.dto.livro.LivroCadastroDto;
import thelibrary.api.biblioteca.enums.GeneroLiterario;


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
    @Enumerated(EnumType.STRING)
    private GeneroLiterario genero;
    private String isbn;
    private Boolean ativo;

    public Livro(LivroCadastroDto livro) {
        this.titulo = livro.titulo();
        this.autor = livro.autor();
        this.anoPublicacao = livro.anoPublicacao();
        this.editora = livro.editora();
        this.genero = livro.genero();
        this.isbn = livro.isbn();
        this.ativo=true;
    }

    public void atualizar(LivroAtualizacaoDto dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.anoPublicacao() != null) {
            this.anoPublicacao = dados.anoPublicacao();
        }
        if(dados.isbn() != null){
            this.isbn = dados.isbn();
        }
        if (dados.editora() != null) {
            this.editora = dados.editora();
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
