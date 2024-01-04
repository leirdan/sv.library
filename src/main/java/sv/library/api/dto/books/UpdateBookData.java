package sv.library.api.dto.books;

import jakarta.validation.constraints.NotNull;

public record UpdateBookData(
        @NotNull
        Long id,
        String title,
        String author,
        String publisher,
        String year,
        Long genreId,
        Long statusId
) {
}
