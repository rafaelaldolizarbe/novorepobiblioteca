package thelibrary.api.biblioteca.dto.book;

import lombok.Builder;

@Builder
public record BookOfWriterDto(
        String isbn,
        String title
) {
}
