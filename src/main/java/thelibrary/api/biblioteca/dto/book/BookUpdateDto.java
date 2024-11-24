package thelibrary.api.biblioteca.dto.book;

import java.time.LocalDate;

public record BookUpdateDto(
        Integer id,
        String isbn,
        Integer literaryGenreId,
        Integer publisherProviderId,
        LocalDate publicationDate,
        String title


) {
}
