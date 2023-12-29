package sv.library.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreData(
        @NotBlank String description
) {
}
