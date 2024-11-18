package thelibrary.api.biblioteca.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thelibrary.api.biblioteca.dto.book.BookGetRequestDto;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.dto.book.BookRequest;
import thelibrary.api.biblioteca.service.book.BookService;

import java.util.List;

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
}