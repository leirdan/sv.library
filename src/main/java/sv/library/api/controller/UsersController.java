package sv.library.api.controller;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import sv.library.api.dto.users.UserDTO;
import sv.library.api.services.repository.IUserRepository;

@RestController
@RequestMapping("/usuarios")
@NoArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UsersController {
    @Autowired
    private IUserRepository _userRepository;

    @GetMapping
    public Page<UserDTO> Index(Pageable page) {
        return _userRepository
                .findAll(page)
                .map(UserDTO::new);
    }
}
