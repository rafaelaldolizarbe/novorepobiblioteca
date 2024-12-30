package thelibrary.api.biblioteca.controller.book;

import jakarta.persistence.EntityNotFoundException;
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
import thelibrary.api.biblioteca.dto.book.BookWithWritersDto;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.dto.book.BookRequest;
import thelibrary.api.biblioteca.entity.Writer;
import thelibrary.api.biblioteca.service.book.BookService;
import thelibrary.api.biblioteca.service.writer.WriterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookservice;
    private final WriterService writerService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody BookRequest request, UriComponentsBuilder uriBuilder
            ) {

        Book savedBook = bookservice.save(request);
        var uri = uriBuilder
                .path("/api/v1/books/{id}")
                .buildAndExpand(
                        savedBook.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<BookGetRequestDto>> findAllBooks() {
        List<BookGetRequestDto> books = bookservice.findAll();

        return ResponseEntity.ok(books);
    }
    @GetMapping("/withwriters/{id}")
    public ResponseEntity<BookWithWritersDto> getBookWithWritersById(@PathVariable Integer id){
        BookWithWritersDto book = bookservice.getBookWithWritersById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookGetRequestDto> detalhar(@PathVariable Integer id){
        BookGetRequestDto book = bookservice.getbookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<BookUpdateDto> atualizar(@RequestBody @Valid BookUpdateDto dados){
        Optional<Book> book = bookservice.getReferenceById(dados.id());
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book response =bookservice.atualizar(dados);
        BookUpdateDto bookUpdateDto = new BookUpdateDto(response);
        return ResponseEntity.ok(bookUpdateDto);

    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> excluir(@PathVariable Integer id){
//        bookservice.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/logico/{id}")
    @Transactional
    public ResponseEntity<BookUpdateDto> desativar(@PathVariable Integer id){
        BookUpdateDto book = bookservice.getdeletebookById(id);
        BookUpdateDto response=bookservice.desativar(book.id());
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{bookId}/assign-writers")
    public ResponseEntity<?> assignWritersToBook(
            @PathVariable Integer bookId,
            @RequestBody List<Integer> writerIds
    ) {

        Book book = bookservice.getReferenceById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + bookId));


        List<Writer> writers = writerService.findAllById(writerIds);
        if (writers.isEmpty()) {
            return ResponseEntity.badRequest().body("No writers found with the provided IDs.");
        }


        book.setWriters(writers);


        bookservice.saveonly(book);


        return ResponseEntity.ok("Writers assigned successfully to the book.");
    }

}