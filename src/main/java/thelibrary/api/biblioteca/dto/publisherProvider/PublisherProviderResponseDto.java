package thelibrary.api.biblioteca.dto.publisherProvider;

import lombok.Builder;

@Builder
public record PublisherProviderResponseDto(
        String publisher_name
) {

}
