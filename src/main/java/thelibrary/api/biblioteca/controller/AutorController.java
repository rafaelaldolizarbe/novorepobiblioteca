package thelibrary.api.biblioteca.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.autor.DadosCadastroAutor;
import thelibrary.api.biblioteca.dto.autor.DadosConsultaAutor;
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
    public void cadastrar(@RequestBody @Valid DadosCadastroAutor dados
//            @RequestBody String json // Considerar que isto é importante pois é outra anotação que refere o que virá a ser recebido no corpo de requisição. É muitíssimo válido não esquecer dessas informações
    ){

        repository.save(new Autor(dados));

    }
    @GetMapping
    public List<DadosConsultaAutor> listar(){
//        List<Autor> autores = repository.findAll();
//        List<DadosConsultaAutor> listagem = autores.stream().map(DadosConsultaAutor::new).toList();
//        return listagem;
        return repository.findAll().stream().map(DadosConsultaAutor::new).toList();
    }
    @GetMapping("/paginacao") // Se achar nescessário pode realizar mais consultas relativas a isto no site do Spring Data JPA procurando por Pageable.
    public Page<DadosConsultaAutor> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosConsultaAutor::new);
    }

    @GetMapping("/ordenado") // Neste exemplo usa-se o @PageableDefault para ordenar por nome. Ele é uma anotação que serve para definir valores padrão para a paginação.
    public Page<DadosConsultaAutor> buscar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosConsultaAutor::new);
    }

}
