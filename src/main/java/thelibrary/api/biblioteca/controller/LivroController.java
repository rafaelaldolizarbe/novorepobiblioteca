package thelibrary.api.biblioteca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/func")
public class LivroController {

    @GetMapping
    public String cumprimentos(){
        return "Está funcionando mesmo";
    }
}
