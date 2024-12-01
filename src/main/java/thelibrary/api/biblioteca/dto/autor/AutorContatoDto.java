package thelibrary.api.biblioteca.dto.autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AutorContatoDto(

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
