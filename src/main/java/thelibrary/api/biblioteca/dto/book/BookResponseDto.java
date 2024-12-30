package thelibrary.api.biblioteca.dto.book;

import lombok.Builder;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.entity.PublisherProvider;

import java.time.LocalDate;
import java.util.List;
@Builder
public record BookResponseDto(

        String isbn,
        LiteraryGenre literaryGenre,
        PublisherProvider publisherProvider,
        LocalDate publicationDate,
        String title


) {
    public BookResponseDto(Book book) {
        this(book.getIsbn(), book.getLiteraryGenre(), book.getPublisherProvider(), book.getPublicationDate(), book.getTitle());
        }

    }

