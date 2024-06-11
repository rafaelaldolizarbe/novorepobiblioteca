package thelibrary.api.biblioteca.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.entity.book.Book;
import thelibrary.api.biblioteca.entity.book.BookRequest;
import thelibrary.api.biblioteca.service.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody BookRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(service.findAll());
    }
}