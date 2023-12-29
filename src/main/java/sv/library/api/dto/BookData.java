package sv.library.api.dto;

import sv.library.api.domain.Book;

public record BookData(
        Long id,
        String title,
        String author,
        String publisher,
        String year,
        String genre,
        String status
) {
    public BookData(Book book) {
        this(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYear(),
                book.getGenre() == null ? null : book.getGenre().getDescription(),
                book.getStatus() == null ? null : book.getStatus().getDescription());
    }
}
