package sv.library.api.dto.books;

import sv.library.api.domain.Book;

import java.time.LocalDateTime;

public record DetailsBookDTO(
        Long id,
        String title,
        String author,
        String year,
        String publisher,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active,
        String genre,
        String status) {
    public DetailsBookDTO(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getPublisher(),
                book.getCreatedAt(),
                book.getUpdatedAt(),
                book.isActive(),
                book.getGenre() == null ? null : book.getGenre().getDescription(),
                book.getStatus() == null ? null : book.getStatus().getDescription());
    }
}
