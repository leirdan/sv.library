package sv.library.api.books.dto;

public record CreateBookData(
        String title,
        String author,
        String publisher,
        String year,
        long genreId,
        long userId,
        long statusId
        ) {
}
