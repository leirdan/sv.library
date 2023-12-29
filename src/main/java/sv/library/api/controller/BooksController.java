package sv.library.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.domain.Book;
import sv.library.api.books.dto.CreateBookData;
import sv.library.api.services.IBookRepository;

@RestController
@RequestMapping("/livros")
public class BooksController {
    @Autowired
    private IBookRepository _bookRepository;

    @PostMapping
    @Transactional
    public void Insert(@RequestBody CreateBookData data) {
        Book book = new Book(data);
        _bookRepository.save(book);
    }
}
