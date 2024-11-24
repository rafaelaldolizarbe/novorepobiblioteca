package thelibrary.api.biblioteca.controller.book;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thelibrary.api.biblioteca.dto.autor.AutorAtualizacaoDto;
import thelibrary.api.biblioteca.dto.autor.AutorDetalhamentoDto;
import thelibrary.api.biblioteca.dto.book.BookGetRequestDto;
import thelibrary.api.biblioteca.dto.book.BookUpdateDto;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.dto.book.BookRequest;
import thelibrary.api.biblioteca.service.book.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody BookRequest request, UriComponentsBuilder uriBuilder
            ) {

        Book savedBook = service.save(request);
        var uri = uriBuilder
                .path("/api/v1/books/{id}")
                .buildAndExpand(
                        savedBook.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<BookGetRequestDto>> findAllBooks() {
        List<BookGetRequestDto> books = service.findAll();

        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookGetRequestDto> detalhar(@PathVariable Integer id){
        BookGetRequestDto book = service.getbookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid BookUpdateDto dados){
        Optional<Book> book = service.getReferenceById(dados.id());
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.atualizar(dados);
        return ResponseEntity.ok(dados);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}