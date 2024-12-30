package thelibrary.api.biblioteca.dto.book;

import lombok.Builder;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreResponseDto;
import thelibrary.api.biblioteca.dto.publisherProvider.PublisherProviderResponseDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;

import java.util.List;

@Builder
public record BookWithWritersDto(

        String isbn,
        LiteraryGenreResponseDto literaryGenre,
        PublisherProviderResponseDto publisherProvider,
        String title,
        List<WriterResponseDto> writers
) {
    public BookWithWritersDto( String isbn, LiteraryGenreResponseDto literaryGenre, PublisherProviderResponseDto publisherProvider, String title, List<WriterResponseDto> writers) {

        this.isbn = isbn;
        this.literaryGenre = literaryGenre;
        this.publisherProvider = publisherProvider;
        this.title = title;
        this.writers = writers;
    }
}
