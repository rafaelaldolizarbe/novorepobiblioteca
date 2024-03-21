package thelibrary.api.biblioteca.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelibrary.api.biblioteca.dto.autor.DadosCadastroAutor;

@RestController
@RequestMapping("/autores")
public class AutorController  {

    @PostMapping // Anotação para atribuir funcionalidades do método post na função que segue
    public void cadastrar(
            @RequestBody DadosCadastroAutor dados
//            @RequestBody String json // Considerar que isto é importante pois é outra anotação que refere o que virá a ser recebido no corpo de requisição. É muitíssimo válido não esquecer dessas informações
    ){
        System.out.println(dados);

    }
}
