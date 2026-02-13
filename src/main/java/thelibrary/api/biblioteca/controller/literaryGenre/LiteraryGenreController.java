package thelibrary.api.biblioteca.controller.literaryGenre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreCreateDto;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.service.literaryGenre.LiteraryGenreService;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LiteraryGenre genre = LiteraryGenre.builder()
                .genreName(request.genre_name())
                .build();
        LiteraryGenre savedGenre = service.save(genre);

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
    @GetMapping("/{id}")
    public ResponseEntity<LiteraryGenre> findById(@PathVariable Integer id) {
        LiteraryGenre genre = service.findById(id);
        return ResponseEntity.ok(genre);
    }
    @PutMapping("/{id}")
//  Método para atualizar um genero literario porem com resposta generica que contêm uma resposta construida.
    public ResponseEntity<?> updateLiteraryGenre(
            @PathVariable Integer id,
            @RequestBody @Valid LiteraryGenreCreateDto request
    ) {
        LiteraryGenre genre = service.findById(id);
        if (genre == null) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("message", "Gênero literário com id " + id + " não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
        genre.setGenreName(request.genre_name());
        service.save(genre);
        return ResponseEntity.ok(genre);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLiteraryGenre(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
