package sv.library.api.dto.genre;

import jakarta.validation.constraints.NotNull;

public record UpdateGenreData(
        @NotNull
        Long id,
        String description
) {
}
