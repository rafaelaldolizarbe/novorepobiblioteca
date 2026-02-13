package thelibrary.api.biblioteca.service.literaryGenre;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.book.BookRequest;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreCreateDto;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.repository.book.BookRepository;
import thelibrary.api.biblioteca.repository.literaryGenre.LiteraryGenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LiteraryGenreService {
    @Autowired
    private LiteraryGenreRepository repository;

    public LiteraryGenre save(LiteraryGenre request) {
        repository.save(request);
        return request;
    }

    public List<LiteraryGenre> findAll() {
        return repository.findAll();
    }

    public LiteraryGenre findById(Integer id) {
        Optional<LiteraryGenre> genre = repository.findById(id);
                if (genre.isEmpty()) {
                    return null;
                }
        return genre
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }
    public LiteraryGenre delete(Integer id){
        LiteraryGenre genre= findById(id);
        repository.delete(genre);
        return genre;
    }
}
