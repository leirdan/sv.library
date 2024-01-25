package sv.library.api.dto.users;

import java.time.LocalDateTime;

import sv.library.api.domain.User;

public record DetailsUserDTO(
        Long id, String name,
        String login,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public DetailsUserDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(),
                user.getCreatedAt(), user.getUpdatedAt());
    }
}
