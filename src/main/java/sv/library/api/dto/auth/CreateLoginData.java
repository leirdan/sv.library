package sv.library.api.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record CreateLoginData(@NotBlank String login, @NotBlank String password) {
}
