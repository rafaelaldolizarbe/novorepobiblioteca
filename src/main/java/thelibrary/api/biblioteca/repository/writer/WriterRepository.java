package thelibrary.api.biblioteca.repository.writer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelibrary.api.biblioteca.entity.Autor;
import thelibrary.api.biblioteca.entity.Writer;

import java.util.List;
import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer, Integer> {

    Page<Writer> findAllByActiveTrue(Pageable paginacao);

    @Query("SELECT w FROM Writer w JOIN FETCH w.books WHERE w.id = :id")
    Optional<Writer> findWriterWithBooks(@Param("id") Integer id);

    @Query("SELECT w.id AS id, w.firstName AS firstName, w.lastName AS lastName, w.description AS description, " +"b.id AS bookId FROM Writer w LEFT JOIN w.books b WHERE w.id = :id")
    List<Object[]> findWriterWithBookIds(@Param("id") Integer id);

//    @Query("SELECT w.id AS id, w.first_name AS firstName, w.last_name AS lastName, w.description AS description, " + "b.id AS bookId FROM Writer w LEFT JOIN w.books b WHERE w.id = :id")
//    List<Object[]> findWriterWithBookIds(@Param("id") Integer id);
}
