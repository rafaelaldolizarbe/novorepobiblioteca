package thelibrary.api.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import thelibrary.api.biblioteca.dto.book.BookRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {
//TO DO - Implementation of the AuditorAware interface to get the current user and set it in the createdBy and lastModifiedBy fields, respectively. Test the implementation and set a test Class whit JUnit 5.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isbn;
    private LocalDate publicationDate;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "literary_genre_id",
            referencedColumnName = "id"
    )
    private LiteraryGenre literaryGenre;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "publisher_id",
            referencedColumnName = "id"
    )
    private PublisherProvider publisherProvider;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private Instant createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant lastModified;


    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;

    @Column(nullable = false)
    private String title;

    public Book(BookRequest bookRequest) {
        this.isbn = bookRequest.isbn();
        this.publicationDate = bookRequest.publicationDate();
        this.title = bookRequest.title();
    }
}