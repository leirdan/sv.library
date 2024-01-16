package sv.library.api.dto.books;

import sv.library.api.domain.Book;

public record BookData(
        Long id,
        String title,
        String author,
        String publisher,
        String year,
        Long genreId,
        Long statusId) {
    public BookData(Book book) {
        this(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYear(),
                book.getGenre() == null ? null : book.getGenre().getId(),
                book.getStatus() == null ? null : book.getStatus().getId());
    }
}
