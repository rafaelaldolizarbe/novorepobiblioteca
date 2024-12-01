package thelibrary.api.biblioteca.repository.literaryGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.LiteraryGenre;

public interface LiteraryGenreRepository extends JpaRepository<LiteraryGenre, Integer> {
}
