package thelibrary.api.biblioteca.controller;

import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.livro.DadosCadastrarLivro;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastrarLivro livro){
        System.out.println(livro);
    }

    @GetMapping
    public String cumprimentos(){
        return "Está funcionando";
    }
}
