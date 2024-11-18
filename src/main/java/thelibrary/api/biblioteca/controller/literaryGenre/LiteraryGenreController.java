package thelibrary.api.biblioteca.controller.literaryGenre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.autor.AutorDetalhamentoDto;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreCreateDto;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.service.literaryGenre.LiteraryGenreService;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/literary-genres")
@RequiredArgsConstructor
public class LiteraryGenreController {
    @Autowired
    private LiteraryGenreService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid LiteraryGenreCreateDto request ,UriComponentsBuilder uriBuilder
    ) {

        LiteraryGenre savedGenre = service.save(request);

        var uri = uriBuilder
                .path("/api/v1/literary-genres/{id}")
                .buildAndExpand(
                        savedGenre.getId())
                .toUri();


        return ResponseEntity.created(uri).body(savedGenre);
    }

    @GetMapping
    public ResponseEntity<List<LiteraryGenre>> findAllLiteraryGenres() {
        List<LiteraryGenre> genres = service.findAll();
        return ResponseEntity.ok(genres);
    }
}
