package sv.library.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.dto.books.CreateBookData;
import sv.library.api.dto.genre.CreateGenreData;
import sv.library.api.dto.genre.GenreData;
import sv.library.api.dto.status.StatusData;
import sv.library.api.services.repository.IBookRepository;
import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.repository.IStatusRepository;
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
}
