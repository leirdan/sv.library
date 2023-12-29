package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.domain.Book;
import sv.library.api.domain.Status;
import sv.library.api.domain.Genre;
import sv.library.api.dto.CreateBookData;
import sv.library.api.services.IBookRepository;
import sv.library.api.services.IGenreRepository;
import sv.library.api.services.IStatusRepository;

@RestController
@RequestMapping("/livros")
public class BooksController {
    @Autowired
    private IBookRepository _bookRepository;
    @Autowired
    private IGenreRepository _genreRepository;
    @Autowired
    private IStatusRepository _statusRepository;

    @PostMapping
    @Transactional
    public void Insert(@RequestBody @Valid CreateBookData data) {
        Genre g = _genreRepository.findById(data.genreId()).orElse(null);
        Status s = _statusRepository.findById(data.statusId()).orElse(null);
        Book book = new Book(data, null, s, g);
        _bookRepository.save(book);
    }
}
