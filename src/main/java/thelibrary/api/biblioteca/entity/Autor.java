package thelibrary.api.biblioteca.entity;


import thelibrary.api.biblioteca.dto.autor.AutorAtualizacaoDto;
import thelibrary.api.biblioteca.dto.autor.AutorCadastroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thelibrary.api.biblioteca.enums.GeneroLiterario;

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
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    @Enumerated(EnumType.STRING) // Indica que os valores que aqui correspondem são limitados a um conjunto de valores pré-definidos, conhecidos como Enumeradores.
    private GeneroLiterario generoLiterario;

    @Embedded
    private Contato contato;
    private Boolean ativo;


    public Autor(AutorCadastroDto dados) {
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.nacionalidade = dados.nacionalidade();
        this.generoLiterario = dados.generoLiterario();
        this.contato = new Contato(dados.contato());
        this.ativo = true;
    }


    public void atualizar(AutorAtualizacaoDto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.nacionalidade() != null) {
            this.nacionalidade = dados.nacionalidade();
        }
        //Faz sentido usar a função acima para substituir uma característica caso o campo seja preenchido, mas não faz sentido para o generoLiterario, pois o generoLiterario é um Enum e não pode ser nulo.

    }

    public void desativar() {
        this.ativo = false;
    }

//    @OneToMany(mappedBy = "escritor")
//    List<Livro> livros;
}
