package thelibrary.api.biblioteca.dto.writer;

import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Writer;

import java.time.LocalDate;

public record WriterResponseDto(
        Integer id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate deathDate,
        String description
) {

    public WriterResponseDto(Writer writer) {
        this(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getBirthDate(), writer.getDeathDate(), writer.getDescription());
    }


}
