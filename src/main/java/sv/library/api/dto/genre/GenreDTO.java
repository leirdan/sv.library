package sv.library.api.dto.genre;

import sv.library.api.domain.Genre;

public record GenreDTO(
        Long id,
        String description) {
    public GenreDTO(Genre genre) {
        this(genre.getId(), genre.getDescription());
    }
}
