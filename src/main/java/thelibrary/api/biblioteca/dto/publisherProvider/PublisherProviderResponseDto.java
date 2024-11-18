package thelibrary.api.biblioteca.dto.publisherProvider;

import lombok.Builder;

@Builder
public record PublisherProviderResponseDto(
        Integer id,
        String publisher_name
) {

}
