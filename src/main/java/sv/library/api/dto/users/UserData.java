package sv.library.api.dto.users;

import java.util.List;

import sv.library.api.domain.Role;
import sv.library.api.domain.User;

public record UserData (
        Long id,
        String name,
        String login,
        List<Role> roles
){
    public UserData(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getRoles());
    }
}
