package sv.library.api.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record CreateLoginDTO(@NotBlank String login, @NotBlank String password) {
}
