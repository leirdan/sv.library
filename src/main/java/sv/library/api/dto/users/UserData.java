package sv.library.api.dto.users;

import sv.library.api.domain.User;

public record UserData (
        Long id,
        String name,
        String login
){
    public UserData(User user) {
        this(user.getId(), user.getName(), user.getLogin());
    }
}
