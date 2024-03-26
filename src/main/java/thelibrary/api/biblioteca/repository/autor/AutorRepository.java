package thelibrary.api.biblioteca.repository.autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import thelibrary.api.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Page<Autor> findAllByAtivoTrue(Pageable paginacao);
}
