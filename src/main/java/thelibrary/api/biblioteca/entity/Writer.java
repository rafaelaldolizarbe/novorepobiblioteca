package thelibrary.api.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Boolean active;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

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

    @ManyToMany(
            mappedBy = "writers"
            ,fetch = FetchType.LAZY
    )
    private List<Book> books;

    public Writer(WriterRequestDto dados) {
        this.firstName = dados.firstName();
        this.lastName = dados.lastName();
        this.birthDate = dados.birthDate();
        this.deathDate = dados.deathDate();
        this.description = dados.description();
        this.active = dados.active();
    }

    public Writer(WriterResponseDto dados) {
        this.firstName = dados.firstName();
        this.lastName = dados.lastName();
        this.birthDate = dados.birthDate();
        this.deathDate = dados.deathDate();
        this.description = dados.description();
        this.active = dados.active();
//        this.ativo = true;
    }
}
