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
        validatorGenre.forEach(g -> g.validate(data.genreId()));
        validatorStatus.forEach(s -> s.validate(data.statusId()));

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
        Status status = null;
        Genre genre = null;

        validatorBooks.forEach(b -> b.validate(data.id()));
        if (data.genreId() != null) {
            validatorGenre.forEach(g -> g.validate(data.genreId()));
            genre = genreRepository.findById(data.genreId()).get();
        }
        if (data.statusId() != null) {
            validatorStatus.forEach(s -> s.validate(data.statusId()));
            status = statusRepository.findById(data.statusId()).get();
        }

        Book book = bookRepository.findById(data.id()).get();

        if (data.title() != null) {
            book.setTitle(data.title());
        }
        if (data.author() != null) {
            book.setAuthor(data.author());
        }
        if (data.publisher() != null) {
            book.setPublisher(data.publisher());
        }
        if (data.year() != null) {
            book.setYear(data.year());
        }
        if (genre != null) {
            book.setGenre(genre);
        }
        if (status != null) {
            book.setStatus(status);
        }

        book.setUpdatedAt(LocalDateTime.now());

        bookRepository.save(book);
        return book;
    }
}
