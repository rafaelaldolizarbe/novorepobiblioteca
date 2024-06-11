package thelibrary.api.biblioteca.repository.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Page<Livro> findAllByAtivoTrue(Pageable paginacao);
}
