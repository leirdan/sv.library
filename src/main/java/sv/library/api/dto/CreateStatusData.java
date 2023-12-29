package sv.library.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateStatusData(
        @NotBlank String description
) {
}
