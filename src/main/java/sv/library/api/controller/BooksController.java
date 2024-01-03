package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.domain.User;
import sv.library.api.dto.BookData;
import sv.library.api.dto.CreateBookData;
import sv.library.api.dto.UpdateBookData;
import sv.library.api.services.IBookRepository;
import sv.library.api.services.IGenreRepository;
import sv.library.api.services.IStatusRepository;
import sv.library.api.services.IUserRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/livros")
public class BooksController {
    @Autowired
    private IBookRepository _bookRepository;
    @Autowired
    private IGenreRepository _genreRepository;
    @Autowired
    private IStatusRepository _statusRepository;
    @Autowired
    private IUserRepository _userRepository;

    @GetMapping
    public Page<BookData> Index(Pageable page) {
        return _bookRepository
                .findAllByActiveTrue(page)
                .map(BookData::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateBookData data) {
        Genre g = _genreRepository.findById(data.genreId()).orElse(null);
        Status s = _statusRepository.findById(data.statusId()).orElse(null);
        Book book = new Book(data, null, s, g);
        _bookRepository.save(book);
    }

    @PutMapping
    @Transactional
    public void Update(@RequestBody @Valid UpdateBookData data) {
        int changes = 0;
        Book book = _bookRepository.getReferenceById(data.id());
        if (book == null) {
        } else {
            if (data.title() != null && data.title() != "") {
                book.setTitle(data.title());
                changes++;
            }
            if (data.author() != null && data.author() != "") {
                book.setAuthor(data.author());
                changes++;
            }
            if (data.publisher() != null && data.publisher() != "") {
                book.setPublisher(data.publisher());
                changes++;
            }
            if (data.year() != null && data.year() != "") {
                book.setYear(data.year());
                changes++;
            }
            if (data.userId() != null) {
                User user = _userRepository.getReferenceById(data.userId());
                if (user != null) {
                    book.setUser(user);
                    changes++;
                }
            }
            if (data.genreId() != null) {
                Genre genre = _genreRepository.getReferenceById(data.genreId());
                if (genre != null) {
                    book.setGenre(genre);
                    changes++;
                }
            }
            if (data.statusId() != null) {
                Status status = _statusRepository.getReferenceById(data.statusId());
                if (status != null) {
                    book.setStatus(status);
                    changes++;
                }
            }

            if (changes > 0) {
                book.setUpdatedAt(LocalDateTime.now());
            }
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void Delete(@PathVariable Long id) {
        Book book = _bookRepository.getReferenceById(id);
        book.setActive(false);
    }

}
