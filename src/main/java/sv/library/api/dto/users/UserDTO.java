package sv.library.api.dto.users;

import java.util.List;

import sv.library.api.domain.Role;
import sv.library.api.domain.User;

public record UserDTO(
        Long id,
        String name,
        String login,
        List<Role> roles) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getRoles());
    }
}
