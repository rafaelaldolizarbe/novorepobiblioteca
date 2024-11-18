package thelibrary.api.biblioteca.controller.publisher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thelibrary.api.biblioteca.dto.publisherProvider.PublisherProviderCreateDto;
import thelibrary.api.biblioteca.entity.PublisherProvider;
import thelibrary.api.biblioteca.service.publisherProvider.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid PublisherProviderCreateDto request, UriComponentsBuilder uriComponentsBuilder
            ){
        PublisherProvider savedPublisher = service.save(request);
        var uri = uriComponentsBuilder
                .path("/api/v1/publishers/{id}")
                .buildAndExpand(
                        savedPublisher.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedPublisher);
    }

    @GetMapping
    public ResponseEntity<List<PublisherProvider>> findAllPublishers() {
        return ResponseEntity.ok(service.findAll());
    }
}
