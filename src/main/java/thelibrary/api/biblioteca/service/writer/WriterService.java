package thelibrary.api.biblioteca.service.writer;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.book.BookOfWriterDto;
import thelibrary.api.biblioteca.dto.book.BookResponseDto;
import thelibrary.api.biblioteca.dto.writer.WriterCreateRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;
import thelibrary.api.biblioteca.dto.writer.WriterWithBooksDto;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.entity.Writer;
import thelibrary.api.biblioteca.repository.book.BookRepository;
import thelibrary.api.biblioteca.repository.writer.WriterRepository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class WriterService {
    @Autowired
    private WriterRepository repository;
    @Autowired
    private BookRepository bookRepository;

    public Writer save(WriterCreateRequestDto request) {
        var writer = Writer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .birthDate(request.birthDate())
                .deathDate(request.deathDate())
                .description(request.description())
                .active(request.active())
                .build();
        repository.save(writer);
        return writer;
    }

    public List<Writer> findAll() {
        return repository.findAll();
    }
    public List<BookResponseDto> getBooksByWriterItemFk(List<Integer> fk) {
        List<Book> listofbooks = bookRepository.findAllById(fk);
        if (listofbooks.isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }
        List<BookResponseDto> listofbooksresponse = listofbooks
                .stream()
                .map(BookResponseDto::new)
                .toList();
        return listofbooksresponse;
    }
    public Page<WriterResponseDto> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable).map(WriterResponseDto::new);
    }

    public Page<WriterResponseDto> findAllByActiveTrue(Pageable pageable) {
        return repository.findAllByActiveTrue( pageable).map(WriterResponseDto::new);
    }

    public WriterResponseDto findById(Integer id) {
        return new WriterResponseDto(repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Writer not found")));
    }
    public List<Writer> findAllById(List<Integer> listid) {
        return repository.findAllById(listid);
    }

    public Writer getReferenceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Writer not found"));
    }

    public Writer update(
            WriterRequestDto request
//            ,Integer id
    ) {
//        var writer = getReferenceById(id);
        Optional<Writer> writer = repository.findById(request.id());
        if (writer.isEmpty()) {
            throw new EntityNotFoundException("Writer not found");

        }
        if (request.firstName()!=null){
            writer.get().setFirstName(request.firstName());
        }
        if (request.lastName()!=null){
            writer.get().setLastName(request.lastName());
        }
        if (request.birthDate()!=null){
            writer.get().setBirthDate(request.birthDate());
        }
        if (request.deathDate()!=null){
            writer.get().setDeathDate(request.deathDate());
        }
        if (request.description()!=null){
            writer.get().setDescription(request.description());
        }


        return repository.save(writer.get());
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Writer desativar(Integer id) {
        var writer =getReferenceById(id);
        writer.setActive(false);
        repository.save(writer);
        return writer;
    }

    public WriterWithBooksDto findWritersWithBooks(Integer id) {
        List<Object[]> results = repository.findWriterWithBookIds(id);
        if (results.isEmpty()) {
            throw new EntityNotFoundException("Writer not found");
        }
        Integer writerId = (Integer) results.get(0)[0];
        String firstName = (String) results.get(0)[1];
        String lastName = (String) results.get(0)[2];
        String description = (String) results.get(0)[3];
        List<Integer> bookIds= new ArrayList<>();
        for (Object[] row : results) {
            if (id == null) {
                id = (Integer) row[0];
                firstName = (String) row[1];
                lastName = (String) row[2];
                description = (String) row[3];
            }
            bookIds.add((Integer) row[4]);
        }

//        List<Integer> ids = writer.getBooks().stream().map(Book::getId).toList();
        List<BookOfWriterDto> listofBooks = bookRepository.findBooksByIds(bookIds);
        return new WriterWithBooksDto(
                firstName,
                lastName,
                description,
                listofBooks
        );
    }
}
