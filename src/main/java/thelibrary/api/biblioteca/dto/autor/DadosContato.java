package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public record DadosContato(

    @NotBlank
    String email,
    String enderecoPostal,
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String telefone,
    String preferenciaDeContato,
    String redeSocial,
    String site,
    Boolean disponibilidadeDeEventos

) {
}
