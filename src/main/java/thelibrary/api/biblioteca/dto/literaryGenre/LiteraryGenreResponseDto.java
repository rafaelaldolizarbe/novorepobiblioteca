package thelibrary.api.biblioteca.dto.literaryGenre;

import lombok.Builder;

@Builder
public record LiteraryGenreResponseDto(
        Integer id,
        String genre_name
) {
}
