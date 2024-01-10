package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sv.library.api.domain.User;
import sv.library.api.dto.users.UserData;
import sv.library.api.services.repository.IUserRepository;
import sv.library.api.dto.users.CreateUserData;

@RestController
@RequestMapping("/usuarios")
@NoArgsConstructor
public class UsersController {
    @Autowired
    private IUserRepository _userRepository;

    @GetMapping
    public Page<UserData> Index(Pageable page) {
        return _userRepository
                .findAll(page)
                .map(UserData::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateUserData data) {
        _userRepository.save(new User(data.name(), data.login(), data.password()));
    }
}
