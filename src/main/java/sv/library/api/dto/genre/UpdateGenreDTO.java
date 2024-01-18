package sv.library.api.dto.genre;

import jakarta.validation.constraints.NotNull;

public record UpdateGenreDTO(
                @NotNull Long id,
                String description) {
}
