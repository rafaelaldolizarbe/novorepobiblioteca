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
import thelibrary.api.biblioteca.entity.Writer;
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
    public Book saveonly( Book book) {
        repository.save(book);
        return book;
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
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }
        return repository.findById(id);
    }

    public Book atualizar(BookUpdateDto dados) {
        Optional<Book> book = getReferenceById(dados.id());
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }
        if(dados.literaryGenreId()!=null)
        {
            LiteraryGenre literaryGenre = getLiteraryGenreByBookItemFk(dados.literaryGenreId());
            book.get().setLiteraryGenre(literaryGenre);
        }
        if(dados.publisherProviderId()!=null) {
            PublisherProvider publisherProvider = getPublisherProviderByBookItemFk(dados.publisherProviderId());
            book.get().setPublisherProvider(publisherProvider);
        }
        if(dados.isbn() != null)
        {
            book.get().setIsbn(dados.isbn());
        }
        if(dados.publicationDate() != null) {
            book.get().setPublicationDate(dados.publicationDate());
        }
        if(dados.publicationDate() != null) {
            book.get().setPublicationDate(dados.publicationDate());
        }
        if (dados.title() != null)
        {
            book.get().setTitle(dados.title());
        }

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

    public BookUpdateDto ToBookUpdateDto(Book book) {
        return new BookUpdateDto(
                book.getId(),
                book.getIsbn(),
                book.getLiteraryGenre().getId(),
                book.getPublisherProvider().getId(),
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
    public BookUpdateDto getdeletebookById(Integer id) {
        BookUpdateDto booksresponse = null;
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            booksresponse = ToBookUpdateDto(book.get());
        }

        return booksresponse;
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public BookUpdateDto desativar(Integer id) {
        Optional<Book> book =getReferenceById(id);
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }
        book.get().setActive(!book.get().getActive());
        return ToBookUpdateDto(repository.save(book.get()));

    }

}