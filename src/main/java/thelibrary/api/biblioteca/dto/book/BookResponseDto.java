package thelibrary.api.biblioteca.dto.book;

import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.entity.PublisherProvider;

import java.util.List;

public record BookResponseDto(

        String isbn,
        LiteraryGenre literaryGenre,
        PublisherProvider publisherProvider,
        String publicationDate,
        String title,
        List<WriterRequestDto> writers
) {
}
