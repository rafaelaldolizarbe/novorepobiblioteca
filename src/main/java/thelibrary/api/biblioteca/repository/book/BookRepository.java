package thelibrary.api.biblioteca.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}