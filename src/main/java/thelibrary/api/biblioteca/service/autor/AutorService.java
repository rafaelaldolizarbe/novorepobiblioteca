package thelibrary.api.biblioteca.service.autor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.autor.AutorCadastroDto;
import thelibrary.api.biblioteca.dto.autor.AutorContatoDto;
import thelibrary.api.biblioteca.entity.Contato;
import thelibrary.api.biblioteca.repository.autor.AutorRepository;
import thelibrary.api.biblioteca.entity.Autor;
@Service
@RequiredArgsConstructor
public class AutorService {
    @Autowired
    private final AutorRepository repository;
    // Propõe-se criar um mapper para a conversão de AutorCadastroDto para Autor, o algoritmo abaixo deverá ser transferido para uma classe Mapper
    public void save(AutorCadastroDto request) {
        AutorContatoDto contato = request.contato();
        Autor autor = Autor.builder()
                .nome(request.nome())
                .dataNascimento(request.dataNascimento())
                .generoLiterario(request.generoLiterario())
                .nacionalidade(request.nacionalidade())
                .contato(Contato.builder()
                        .email(contato.email())
                        .telefone(contato.telefone())
                        .build())
                .build();
        repository.save(autor);
    }
}
