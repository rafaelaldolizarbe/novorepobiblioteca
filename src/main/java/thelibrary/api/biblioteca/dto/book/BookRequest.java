package thelibrary.api.biblioteca.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
public record BookRequest(
        @NotBlank
         String isbn,
        @NotNull
         Integer literaryGenreId,
        @NotNull
         Integer publisherProviderId,
        @NotNull
         LocalDate publicationDate,
        @NotBlank
         String title
) {

}
