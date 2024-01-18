package sv.library.api.dto.genre;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDTO(
                @NotBlank String description) {
}
