package sv.library.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.dto.books.CreateBookData;
import sv.library.api.dto.books.UpdateBookData;
import sv.library.api.dto.genre.GenreData;
import sv.library.api.dto.status.StatusData;
import sv.library.api.services.repository.IBookRepository;
import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.repository.IStatusRepository;
import sv.library.api.services.validations.interfaces.IValidatorBook;
import sv.library.api.services.validations.interfaces.IValidatorGenre;
import sv.library.api.services.validations.interfaces.IValidatorStatus;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IStatusRepository statusRepository;
    @Autowired
    private IGenreRepository genreRepository;
    @Autowired
    private List<IValidatorGenre> validatorGenre;
    @Autowired
    private List<IValidatorStatus> validatorStatus;
    @Autowired
    private List<IValidatorBook> validatorBooks;

    public Book create(CreateBookData data) {
        validatorGenre.forEach(g -> g.validate(new GenreData(data.genreId(), null)));
        validatorStatus.forEach(s -> s.validate(new StatusData(data.statusId(), null)));

        try {
            Status status = statusRepository.findById(data.statusId()).get();
            Genre genre = genreRepository.findById(data.genreId()).get();

            Book book = new Book(data, status, genre);
            bookRepository.save(book);

            return book;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Book update(UpdateBookData data) {
        validatorBooks.forEach(b -> b.validate(data.id()));

        Book book = bookRepository.findById(data.id()).get();
        Book bookUpdated = new Book(book.getId(), data.title(), data.author(), data.publisher(), data.year(), null,
                LocalDateTime.now(), true, null, null);
        // Adicionar atualização de gênero e status.
        bookUpdated.setUpdatedAt(LocalDateTime.now());
        bookRepository.updateBook(bookUpdated);

        return book;
    }
}
