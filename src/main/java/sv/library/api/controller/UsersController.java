package sv.library.api.controller;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import sv.library.api.domain.User;
import sv.library.api.dto.users.DetailsUserDTO;
import sv.library.api.dto.users.UpdateUserDTO;
import sv.library.api.dto.users.UserDTO;
import sv.library.api.services.UserService;

@RestController
@RequestMapping("/usuarios")
@NoArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> Index(Pageable page) {
        Page<UserDTO> users = userService
                .findAll(page)
                .map(UserDTO::new);

        return ResponseEntity.ok(users);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailsUserDTO> Update(@RequestBody UpdateUserDTO data) {
        User user = userService.update(data);
        return ResponseEntity.ok(new DetailsUserDTO(user));
    }
}
