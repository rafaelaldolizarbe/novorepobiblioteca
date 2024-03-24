package thelibrary.api.biblioteca.entity;


import thelibrary.api.biblioteca.dto.autor.DadosCadastroAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;

import java.time.LocalDate;

@Table(name = "autor")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor do ID será gerado automaticamente.
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    @Enumerated(EnumType.STRING) // Indica que os valores que aqui correspondem são limitados a um conjunto de valores pré-definidos, conhecidos como Enumeradores.
    private GeneroLiterario generoLiterario;

    @Embedded
    private Contato contato;

    public Autor(DadosCadastroAutor dados) {
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.nacionalidade = dados.nacionalidade();
        this.generoLiterario = dados.generoLiterario();
        this.contato = new Contato(dados.contato());
    }

//    @OneToMany(mappedBy = "escritor")
//    List<Livro> livros;
}
