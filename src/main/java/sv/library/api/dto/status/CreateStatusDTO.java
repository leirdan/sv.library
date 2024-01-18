package sv.library.api.dto.status;

import jakarta.validation.constraints.NotBlank;

public record CreateStatusDTO(
                @NotBlank String description) {
}
