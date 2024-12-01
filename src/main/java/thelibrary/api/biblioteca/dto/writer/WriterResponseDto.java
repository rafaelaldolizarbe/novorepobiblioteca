package thelibrary.api.biblioteca.dto.writer;

import lombok.Builder;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Writer;

import java.time.LocalDate;
@Builder
public record WriterResponseDto(
        Integer id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate deathDate,
        String description,

        Boolean active
) {

    public WriterResponseDto(Writer writer) {
        this(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getBirthDate(), writer.getDeathDate(), writer.getDescription(),writer.getActive());
    }


}
