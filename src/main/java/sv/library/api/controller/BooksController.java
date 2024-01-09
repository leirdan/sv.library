package sv.library.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.dto.books.BookData;
import sv.library.api.dto.books.CreateBookData;
import sv.library.api.dto.books.DetailsBookData;
import sv.library.api.dto.books.UpdateBookData;
import sv.library.api.services.IBookRepository;
import sv.library.api.services.IGenreRepository;
import sv.library.api.services.IStatusRepository;
import sv.library.api.services.IUserRepository;

import java.net.URI;
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
    public ResponseEntity<Page<BookData>> Index(Pageable page) {
        Page<BookData> books = _bookRepository
                .findAllByActiveTrue(page)
                .map(BookData::new);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookData> GetOne(@PathVariable Long id) {
        Book book = _bookRepository.getReferenceById(id);

        if (book.isActive()) {
            return ResponseEntity.ok(new BookData(book));
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity Create(@RequestBody @Valid CreateBookData data, UriComponentsBuilder builder) {
        Genre g = _genreRepository.findById(data.genreId()).orElse(null);
        if (g == null) { throw new EntityNotFoundException("Genre doesn't exist!"); }
        Status s = _statusRepository.findById(data.statusId()).orElse(null);
        if (s == null) { throw new EntityNotFoundException("Status doesn't exist!"); }

        Book book = new Book(data, s, g);

        _bookRepository.save(book);

        URI uri = builder.path("/livros/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsBookData(book));
    }

    @PutMapping
    @Transactional
    public ResponseEntity Update(@RequestBody @Valid UpdateBookData data) {
        int changes = 0;
        Book book = _bookRepository.getReferenceById(data.id());
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

        return ResponseEntity.ok(new DetailsBookData(book));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Delete(@PathVariable Long id) {
        Book book = _bookRepository.getReferenceById(id);
        book.setActive(false);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity Activate(@PathVariable Long id) {
        Book book = _bookRepository.getReferenceById(id);

        if (!book.isActive()) {
            book.setActive(true);
            book.setUpdatedAt(LocalDateTime.now());
        }

        return ResponseEntity.ok(new DetailsBookData(book));

    }

}
