package thelibrary.api.biblioteca.repository.writer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Writer;

public interface WriterRepository extends JpaRepository<Writer, Integer> {

    Page<Writer> findAllByAtivoTrue(Pageable paginacao);
}
