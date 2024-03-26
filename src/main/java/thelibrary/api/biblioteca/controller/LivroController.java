package thelibrary.api.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.livro.DadosCadastrarLivro;
import thelibrary.api.biblioteca.entity.Livro;
import thelibrary.api.biblioteca.repository.livro.LivroRepository;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastrarLivro livro){

        livroRepository.save(new Livro(livro));
    }

    @GetMapping
    public List<Livro> listartudo(){

        return livroRepository.findAll();
    }


}
