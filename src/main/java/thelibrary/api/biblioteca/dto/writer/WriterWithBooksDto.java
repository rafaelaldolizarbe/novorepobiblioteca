package thelibrary.api.biblioteca.dto.writer;

import lombok.Builder;
import thelibrary.api.biblioteca.dto.book.BookOfWriterDto;
import thelibrary.api.biblioteca.dto.book.BookResponseDto;
import thelibrary.api.biblioteca.entity.Writer;

import java.util.List;
@Builder
public record WriterWithBooksDto(

        String firstName,
        String lastName,
        String description,
        List<BookOfWriterDto> listOfBooks
) {
    public WriterWithBooksDto( String firstName, String lastName, String description, List<BookOfWriterDto> listOfBooks) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.listOfBooks = listOfBooks;

    }
}
