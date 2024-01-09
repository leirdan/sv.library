package sv.library.api.dto.genre;

import sv.library.api.domain.Genre;

import java.time.LocalDateTime;

public record DetailsGenreData(Long id, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public DetailsGenreData(Genre genre) {
        this(
                genre.getId(),
                genre.getDescription(),
                genre.getCreatedAt(),
                genre.getUpdatedAt()
        );
    }
}
