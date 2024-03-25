package thelibrary.api.biblioteca.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Autor> autores = repository.findAll();
        List<DadosConsultaAutor> listagem = autores.stream().map(DadosConsultaAutor::new).toList();
        return listagem;
    }
}
