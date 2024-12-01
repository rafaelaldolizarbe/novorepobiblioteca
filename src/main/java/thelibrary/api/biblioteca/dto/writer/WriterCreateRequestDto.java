package thelibrary.api.biblioteca.dto.writer;

import lombok.Builder;
import thelibrary.api.biblioteca.entity.Writer;

import java.time.LocalDate;

@Builder
public record WriterCreateRequestDto(

        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate deathDate,
        String description,
        Boolean active
) {
    public WriterCreateRequestDto(Writer writer) {
        this(writer.getFirstName(), writer.getLastName(), writer.getBirthDate(), writer.getDeathDate(), writer.getDescription(),writer.getActive());
    }

    public static WriterCreateRequestDto toDto(Writer writer){
        return new WriterCreateRequestDto( writer.getFirstName(), writer.getLastName(), writer.getBirthDate(), writer.getDeathDate(), writer.getDescription(),writer.getActive());
    }
}
