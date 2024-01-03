package sv.library.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateBookData(
        @NotNull
        Long id,
        String title,
        String author,
        String publisher,
        String year,
        Long userId,
        Long genreId,
        Long statusId
) {
}
