package thelibrary.api.biblioteca.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreCreateDto;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreResponseDto;
import thelibrary.api.biblioteca.dto.publisherProvider.PublisherProviderResponseDto;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.entity.PublisherProvider;

import java.time.LocalDate;

public record BookGetRequestDto(

        @NotBlank
        String isbn,
        @NotNull
        LiteraryGenreResponseDto literaryGenre,
        @NotNull
        PublisherProviderResponseDto publisherProvider,
        @NotNull
        LocalDate publicationDate,
        @NotBlank
        String title

) {
}
