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
import sv.library.api.dto.books.BookDTO;
import sv.library.api.dto.books.CreateBookDTO;
import sv.library.api.dto.books.DetailsBookDTO;
import sv.library.api.dto.books.UpdateBookDTO;
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
    public ResponseEntity<Page<BookDTO>> Index(Pageable page) {
        Page<BookDTO> books = bookRepository
                .findAllByActiveTrue(page)
                .map(BookDTO::new);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsBookDTO> GetOne(@PathVariable Long id) {
        Book book = bookService.get(id);

        return book == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(new DetailsBookDTO(book));
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsBookDTO> Create(@RequestBody @Valid CreateBookDTO data,
            UriComponentsBuilder builder) {
        Book book = bookService.create(data);

        URI uri = builder.path("/livros/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsBookDTO(book));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsBookDTO> Update(@RequestBody @Valid UpdateBookDTO data) {
        Book book = bookService.update(data);
        return ResponseEntity.ok(new DetailsBookDTO(book));
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
    public ResponseEntity<DetailsBookDTO> Activate(@PathVariable Long id) {
        Book book = bookRepository.getReferenceById(id);

        if (!book.isActive()) {
            book.setActive(true);
            book.setUpdatedAt(LocalDateTime.now());
        }

        return ResponseEntity.ok(new DetailsBookDTO(book));

    }

}
