package thelibrary.api.biblioteca.entity;


import lombok.*;
import thelibrary.api.biblioteca.entity.enumerable.GeneroLiterario;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

//    @OneToMany(mappedBy = "escritor")
//    List<Livro> livros;
}
