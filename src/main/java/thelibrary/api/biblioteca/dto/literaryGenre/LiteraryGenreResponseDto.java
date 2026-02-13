package thelibrary.api.biblioteca.dto.literaryGenre;

import lombok.Builder;

@Builder
public record LiteraryGenreResponseDto(
        String genre_name
) {
}
