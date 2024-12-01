package thelibrary.api.biblioteca.service.writer;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.writer.WriterCreateRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;
import thelibrary.api.biblioteca.entity.Writer;
import thelibrary.api.biblioteca.repository.writer.WriterRepository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Transactional
public class WriterService {
    @Autowired
    private WriterRepository repository;

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
}
