package thelibrary.api.biblioteca.dto.literaryGenre;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record LiteraryGenreCreateDto(
        String genre_name
) {

}
