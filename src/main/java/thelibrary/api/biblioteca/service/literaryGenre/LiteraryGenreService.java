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

@Service
@RequiredArgsConstructor
public class LiteraryGenreService {
    @Autowired
    private LiteraryGenreRepository repository;

    public LiteraryGenre save(LiteraryGenreCreateDto request) {
        var genre = LiteraryGenre.builder()
                .genreName(request.genre_name())
                .build();
        repository.save(genre);
        return genre;
    }

    public List<LiteraryGenre> findAll() {
        return repository.findAll();
    }

}
