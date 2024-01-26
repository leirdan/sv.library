package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.library.api.domain.Role;
import sv.library.api.domain.User;
import sv.library.api.dto.auth.CreateLoginDTO;
import sv.library.api.dto.auth.TokenDTO;
import sv.library.api.dto.users.CreateUserDTO;
import sv.library.api.dto.users.DetailsUserDTO;
import sv.library.api.infra.SecurityConfiguration;
import sv.library.api.services.TokenService;
import sv.library.api.services.UserService;
import sv.library.api.services.impl.UserDetailsImpl;
import sv.library.api.services.repository.IRoleRepository;
import sv.library.api.services.repository.IUserRepository;

@RestController
@RequestMapping("/auth")
@NoArgsConstructor
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecurityConfiguration securityConfig;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid CreateLoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authManager.authenticate(authToken);

        String jwtToken = tokenService.generateToken((UserDetailsImpl) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(jwtToken));
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<DetailsUserDTO> register(@RequestBody @Valid CreateUserDTO data) throws ValidationException {
        List<Role> roles = new ArrayList<Role>();
        Role role = roleRepository.findByName(data.role());
        roles.add(role);

        User newUser = User
                .builder()
                .name(data.name())
                .login(data.login())
                .password(securityConfig.encoder().encode(data.password()))
                .active(true)
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        boolean result = userService.isUserCreated(newUser);

        if (result == false) {
            userRepository.save(newUser);
            return ResponseEntity.ok(new DetailsUserDTO(newUser));
        } else {
            throw new ValidationException("User already created!");
        }
    }
}
