package thelibrary.api.biblioteca.controller.autor;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thelibrary.api.biblioteca.dto.autor.*;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.repository.autor.AutorRepository;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController  {
    @Autowired
    private AutorRepository repository;
    @PostMapping // Anotação para atribuir funcionalidades do método post na função que segue
    @Transactional // Anotação para que o método seja executado dentro de uma transação
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorCadastroDto dados, UriComponentsBuilder uriBuilder
//            @RequestBody String json // Considerar que isto é importante pois é outra anotação que refere o que virá a ser recebido no corpo de requisição. É muitíssimo válido não esquecer dessas informações
    ){
        Autor autor = new Autor(dados);
        repository.save(autor);

        var uri = uriBuilder
                .path("/autores/{id}")
                .buildAndExpand(autor
                        .getId()).toUri();

        AutorDetalhamentoDto dto = new AutorDetalhamentoDto(autor);

        return ResponseEntity.created(uri).body(dto);

    }
    @GetMapping
    public ResponseEntity<List<AutorConsultaDto>> listar(){
//        List<Autor> autores = repository.findAll();
//        List<DadosConsultaAutor> listagem = autores.stream().map(DadosConsultaAutor::new).toList();
//        return listagem;
        List<AutorConsultaDto> resposta=repository.findAll().stream().map(AutorConsultaDto::new).toList();
        return ResponseEntity.ok(resposta);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AutorDetalhamentoDto> detalhar(@PathVariable Long id){
        Autor autor = repository.findById(id).orElseThrow();
        return ResponseEntity.ok(new AutorDetalhamentoDto(autor));
    }


    @GetMapping("/logico")
    public ResponseEntity<Page<AutorConsultaDto>> consultaLogica(Pageable paginacao){
        Page<AutorConsultaDto> resposta = repository.findAllByAtivoTrue(paginacao).map(AutorConsultaDto::new);
        return ResponseEntity.ok(resposta);
    }
    @GetMapping("/paginacao") // Se achar nescessário pode realizar mais consultas relativas a isto no site do Spring Data JPA procurando por Pageable.
    public ResponseEntity<Page<AutorConsultaDto>> listar(Pageable paginacao){
        Page<AutorConsultaDto> resposta = repository.findAll(paginacao).map(AutorConsultaDto::new);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/ordenado") // Neste exemplo usa-se o @PageableDefault para ordenar por nome. Ele é uma anotação que serve para definir valores padrão para a paginação.
    public Page<AutorConsultaDto> buscar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(AutorConsultaDto::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AutorAtualizacaoDto dados){
        Autor autor = repository.getReferenceById(dados.id());
        autor.atualizar(dados);
        return ResponseEntity.ok(new AutorDetalhamentoDto(autor));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logico/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        Autor autor = repository.getReferenceById(id);
        autor.desativar();
        return ResponseEntity.noContent().build();
    }

}
