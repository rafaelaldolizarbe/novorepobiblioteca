package thelibrary.api.biblioteca.repository.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thelibrary.api.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
