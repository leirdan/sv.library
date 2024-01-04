package sv.library.api.dto.users;

import jakarta.validation.constraints.NotBlank;

public record CreateUserData(
        @NotBlank
        String name,
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
