package thelibrary.api.biblioteca.dto.autor;

public record DadosContato(
    String email,
    String enderecoPostal,
    String telefone,
    String preferenciaDeContato,
    String redeSocial,
    String site,
    Boolean disponibilidadeDeEventos

) {
}
