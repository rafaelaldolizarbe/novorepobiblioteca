package thelibrary.api.biblioteca.entity;

import lombok.Builder;
import thelibrary.api.biblioteca.dto.autor.AutorContatoDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // Indica que a classe é um componente de outra entidade e serve para que o JPA associe os atributos da classe com os atributos da entidade que a contém. Ou seja na entidade estes atributos estarão contidos.
@Getter
@Builder
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

    public Contato(AutorContatoDto contato) {
        this.email = contato.email();
        this.enderecoPostal = contato.enderecoPostal();
        this.telefone = contato.telefone();
        this.preferenciaDeContato = contato.preferenciaDeContato();
        this.redeSocial = contato.redeSocial();
        this.site = contato.site();
        this.disponibilidadeDeEventos = contato.disponibilidadeDeEventos();
    }
}
