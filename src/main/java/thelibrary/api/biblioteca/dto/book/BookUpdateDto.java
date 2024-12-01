package thelibrary.api.biblioteca.dto.book;

import thelibrary.api.biblioteca.entity.Book;

import java.time.LocalDate;

public record BookUpdateDto(
        Integer id,
        String isbn,
        Integer literaryGenreId,
        Integer publisherProviderId,
        LocalDate publicationDate,
        String title


) {
    public BookUpdateDto(Book response) {
        this(response.getId(), response.getIsbn(), response.getLiteraryGenre().getId(), response.getPublisherProvider().getId(), response.getPublicationDate(), response.getTitle());
    }
}
