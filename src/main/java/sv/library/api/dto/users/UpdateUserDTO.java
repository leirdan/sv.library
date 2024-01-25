package sv.library.api.dto.users;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(@NotNull Long id, String name, String login) {
}