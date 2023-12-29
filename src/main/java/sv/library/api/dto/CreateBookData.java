package sv.library.api.dto;

public record CreateBookData(
        String title,
        String author,
        String publisher,
        String year,
        Long genreId,
        Long userId,
        Long statusId
        ) {
}
