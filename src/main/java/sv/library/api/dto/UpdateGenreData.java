package sv.library.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateGenreData(
        @NotNull
        Long id,
        String description
) {
}
