package thelibrary.api.biblioteca.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // Indica que a classe é um componente de outra entidade e serve para que o JPA associe os atributos da classe com os atributos da entidade que a contém. Ou seja na entidade estes atributos estarão contidos.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    private String email;
    private String enderecoPostal;
    private String telefone;
    private String preferenciaDeContato;
    private String redeSocial;
    private String site;
    private Boolean disponibilidadeDeEventos;
}
