package thelibrary.api.biblioteca.service.publisherProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.publisherProvider.PublisherProviderCreateDto;
import thelibrary.api.biblioteca.entity.PublisherProvider;
import thelibrary.api.biblioteca.repository.publisherProvider.PublisherRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PublisherService {
    @Autowired
    private PublisherRepository repository;

    public PublisherProvider save(PublisherProviderCreateDto dto){
        var publisher = PublisherProvider.builder()
                .publisherName(dto.publisherName())
                .build();
        repository.save(publisher);
        return publisher;
    }

    public List<PublisherProvider> findAll() {return repository.findAll();}
}
