package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import sv.library.api.domain.Book;
import sv.library.api.dto.books.BookData;
import sv.library.api.dto.books.CreateBookData;
import sv.library.api.dto.books.DetailsBookData;
import sv.library.api.dto.books.UpdateBookData;
import sv.library.api.services.BookService;
import sv.library.api.services.repository.IBookRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/livros")
@NoArgsConstructor
public class BooksController {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookData>> Index(Pageable page) {
        Page<BookData> books = bookRepository
                .findAllByActiveTrue(page)
                .map(BookData::new);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsBookData> GetOne(@PathVariable Long id) {
        Book book = bookService.get(id);

        return book == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(new DetailsBookData(book));
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsBookData> Create(@RequestBody @Valid CreateBookData data,
            UriComponentsBuilder builder) {
        Book book = bookService.create(data);

        URI uri = builder.path("/livros/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsBookData(book));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsBookData> Update(@RequestBody @Valid UpdateBookData data) {
        Book book = bookService.update(data);
        return ResponseEntity.ok(new DetailsBookData(book));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        Book book = bookRepository.getReferenceById(id);
        book.setActive(false);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsBookData> Activate(@PathVariable Long id) {
        Book book = bookRepository.getReferenceById(id);

        if (!book.isActive()) {
            book.setActive(true);
            book.setUpdatedAt(LocalDateTime.now());
        }

        return ResponseEntity.ok(new DetailsBookData(book));

    }

}
