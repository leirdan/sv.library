package sv.library.api.dto.genre;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreData(
        @NotBlank String description
) {
}
