package sv.library.api.dto.genre;

import sv.library.api.domain.Genre;

public record GenreData(
        Long id,
        String description
) {
    public GenreData(Genre genre) {
        this(genre.getId(), genre.getDescription());
    }
}
