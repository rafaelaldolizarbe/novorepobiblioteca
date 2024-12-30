package thelibrary.api.biblioteca.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelibrary.api.biblioteca.dto.book.BookOfWriterDto;
import thelibrary.api.biblioteca.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT new thelibrary.api.biblioteca.dto.book.BookOfWriterDto(b.isbn, b.title) FROM Book b WHERE b.id IN :bookIds")
    List<BookOfWriterDto> findBooksByIds(@Param("bookIds") List<Integer> bookIds);

}