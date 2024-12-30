package thelibrary.api.biblioteca.dto.publisherProvider;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public record PublisherProviderCreateDto(
        String publisherName
) {

}
