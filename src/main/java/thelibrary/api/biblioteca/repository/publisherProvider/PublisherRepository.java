package thelibrary.api.biblioteca.repository.publisherProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.PublisherProvider;

public interface PublisherRepository extends JpaRepository<PublisherProvider,Integer > {
}
