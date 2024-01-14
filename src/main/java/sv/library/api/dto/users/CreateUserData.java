package sv.library.api.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sv.library.api.utils.RoleName;

public record CreateUserData(
                @NotBlank String name,
                @NotBlank String login,
                @NotBlank String password,
                @NotNull RoleName role) {
}
