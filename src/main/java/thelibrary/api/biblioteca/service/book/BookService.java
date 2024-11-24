package thelibrary.api.biblioteca.service.book;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thelibrary.api.biblioteca.dto.book.BookGetRequestDto;
import thelibrary.api.biblioteca.dto.book.BookUpdateDto;
import thelibrary.api.biblioteca.dto.literaryGenre.LiteraryGenreResponseDto;
import thelibrary.api.biblioteca.dto.publisherProvider.PublisherProviderResponseDto;
import thelibrary.api.biblioteca.entity.Book;
import thelibrary.api.biblioteca.dto.book.BookRequest;
import thelibrary.api.biblioteca.entity.LiteraryGenre;
import thelibrary.api.biblioteca.entity.PublisherProvider;
import thelibrary.api.biblioteca.repository.book.BookRepository;
import thelibrary.api.biblioteca.repository.literaryGenre.LiteraryGenreRepository;
import thelibrary.api.biblioteca.repository.publisherProvider.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository repository;

    private final LiteraryGenreRepository literaryGenreRepository;

    private final PublisherRepository publisherRepository;

    public LiteraryGenre getLiteraryGenreByBookItemFk(Integer fk) {
        return literaryGenreRepository.findById(fk).orElseThrow(() -> new EntityNotFoundException("Literary Genre not found"));
    }

    public PublisherProvider getPublisherProviderByBookItemFk(Integer fk) {
        return publisherRepository.findById(fk).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
    }

    public Book save(BookRequest request) {
        LiteraryGenre literaryGenre = getLiteraryGenreByBookItemFk(request.literaryGenreId());
        PublisherProvider publisherProvider = getPublisherProviderByBookItemFk(request.publisherProviderId());

        var book = Book.builder()
                .isbn(request.isbn())
                .literaryGenre(literaryGenre)
                .publisherProvider(publisherProvider)
                .publicationDate(request.publicationDate())
                .title(request.title())
                .build();
        repository.save(book);
        return book;
    }
    public Optional<Book> getReferenceById(Integer id) {
        return repository.findById(id);
    }

    public Book atualizar(BookUpdateDto dados) {
        Optional<Book> book = getReferenceById(dados.id());
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }
        LiteraryGenre literaryGenre = getLiteraryGenreByBookItemFk(dados.literaryGenreId());
        PublisherProvider publisherProvider = getPublisherProviderByBookItemFk(dados.publisherProviderId());
        book.get().setIsbn(dados.isbn());
        book.get().setLiteraryGenre(literaryGenre);
        book.get().setPublisherProvider(publisherProvider);
        book.get().setPublicationDate(dados.publicationDate());
        book.get().setTitle(dados.title());
        return repository.save(book.get());
    }

    public BookGetRequestDto ToDto(Book book) {
        LiteraryGenre literaryGenre = getLiteraryGenreByBookItemFk(book.getLiteraryGenre().getId());
        LiteraryGenreResponseDto genredto = null;
        PublisherProvider publisherProvider = getPublisherProviderByBookItemFk(book.getPublisherProvider().getId());
        PublisherProviderResponseDto publisherdto = null;
        if (literaryGenre != null) {
            genredto = LiteraryGenreResponseDto.builder()
                    .id(literaryGenre.getId())
                    .genre_name(literaryGenre.getGenreName())
                    .build();
        }
        if(publisherProvider != null){
            publisherdto = PublisherProviderResponseDto.builder()
                    .id(publisherProvider.getId())
                    .publisher_name(publisherProvider.getPublisherName())
                    .build();
        }
        return new BookGetRequestDto(
                book.getIsbn(),
                genredto,
                publisherdto,
                book.getPublicationDate(),
                book.getTitle()
        );
    }

    public List<BookGetRequestDto> findAll() {
        List<BookGetRequestDto> booksresponse = new ArrayList<>();

        List<Book> books = repository.findAll();
        for (Book bok : books) {
            booksresponse.add(ToDto(bok));
        }
        return booksresponse;
    }
    public BookGetRequestDto getbookById(Integer id) {
        BookGetRequestDto booksresponse = null;
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            booksresponse = ToDto(book.get());
        }

        return booksresponse;
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}