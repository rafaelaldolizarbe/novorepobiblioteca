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
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreCreateDto;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LiteraryGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String genreName;

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


    public LiteraryGenre(LiteraryGenreCreateDto dados) {

        this.genreName = dados.genre_name();

    }
}
