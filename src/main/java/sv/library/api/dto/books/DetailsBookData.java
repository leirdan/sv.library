package sv.library.api.dto.books;

import sv.library.api.domain.Book;

import java.time.LocalDateTime;

public record DetailsBookData(
        Long id,
        String title,
        String author,
        String year,
        String publisher,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active,
        Long genre,
        Long status,
        Long user
        ) {
    public DetailsBookData(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getPublisher(),
                book.getCreatedAt(),
                book.getUpdatedAt(),
                book.isActive(),
                book.getGenre() == null ? null : book.getGenre().getId(),
                book.getStatus() == null ? null: book.getStatus().getId(),
                book.getUser() == null ? null : book.getUser().getId()
        );
    }
}
