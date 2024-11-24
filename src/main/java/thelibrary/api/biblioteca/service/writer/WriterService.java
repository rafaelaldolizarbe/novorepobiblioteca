package thelibrary.api.biblioteca.service.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;
import thelibrary.api.biblioteca.entity.Writer;
import thelibrary.api.biblioteca.repository.writer.WriterRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class WriterService {
    @Autowired
    private WriterRepository repository;

    public Writer save(WriterRequestDto request) {
        var writer = Writer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .birthDate(request.birthDate())
                .deathDate(request.deathDate())
                .description(request.description())
                .build();
        repository.save(writer);
        return writer;
    }

    public List<Writer> findAll() {
        return repository.findAll();
    }

    public Page<WriterResponseDto> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable).map(WriterResponseDto::new);
    }

    public Page<WriterResponseDto> findAllByAtivoTrue(Pageable pageable) {
        return repository.findAllByAtivoTrue( pageable).map(WriterResponseDto::new);
    }
}
