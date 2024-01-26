package sv.library.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import sv.library.api.domain.User;
import sv.library.api.dto.users.UpdateUserDTO;
import sv.library.api.services.repository.IUserRepository;
import sv.library.api.services.validations.interfaces.IValidatorUser;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private List<IValidatorUser> validatorUser;

    public Page<User> findAll(Pageable page) {
        return userRepository.findAllByActiveTrue(page);
    }

    public User update(UpdateUserDTO data) {
        validatorUser.forEach(u -> u.validate(data.id()));

        User user = userRepository.findById(data.id()).get();
        int changes = 0;

        if (data.login() != null) {
            user.setLogin(data.login());
            changes++;
        }

        if (data.name() != null) {
            user.setName(data.name());
            changes++;
        }

        if (changes != 0) {
            user.setUpdatedAt(LocalDateTime.now());
        }

        userRepository.save(user);
        return user;
    }

    public boolean isUserCreated(User user) {
        return userRepository.findByLogin(user.getLogin()).isPresent();
    }
}
