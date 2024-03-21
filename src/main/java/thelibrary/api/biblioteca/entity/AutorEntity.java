package thelibrary.api.biblioteca.entity;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nome;
    @NotNull
    private String generoLiterario;
    @OneToMany(mappedBy = "escritor")
    List<LivroEntity> livros;
}
