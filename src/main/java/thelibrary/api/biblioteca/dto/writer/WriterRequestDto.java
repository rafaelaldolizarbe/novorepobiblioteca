package thelibrary.api.biblioteca.dto.writer;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WriterRequestDto(
        Integer id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate deathDate,
        String description
) {
}
