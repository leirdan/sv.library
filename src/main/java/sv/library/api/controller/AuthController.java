package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
import sv.library.api.dto.auth.CreateLoginData;
import sv.library.api.dto.auth.TokenData;
import sv.library.api.dto.users.CreateUserData;
import sv.library.api.infra.SecurityConfiguration;
import sv.library.api.services.TokenService;
import sv.library.api.services.impl.UserDetailsImpl;
import sv.library.api.services.repository.IUserRepository;

@RestController
@RequestMapping("/auth")
@NoArgsConstructor
public class AuthController {
    @Autowired
    private AuthenticationManager _authManager;
    @Autowired
    private TokenService _tokenService;
    @Autowired
    private SecurityConfiguration _securityConfig;
    @Autowired
    private IUserRepository _userRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenData> login(@RequestBody @Valid CreateLoginData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = _authManager.authenticate(authToken);

        String jwtToken = _tokenService.generateToken((UserDetailsImpl) auth.getPrincipal());
        System.out.println(jwtToken);

        return ResponseEntity.ok(new TokenData(jwtToken));
    }

    @PostMapping("/cadastro")
    @Transactional
    public void register(@RequestBody @Valid CreateUserData data) {
        User user = User
                .builder()
                .name(data.name())
                .login(data.login())
                .password(_securityConfig.encoder().encode(data.password()))
                .roles(List.of(Role
                        .builder()
                        .name(data.role())
                        .build()))
                .createdAt(LocalDateTime.now())
                .build();

        _userRepository.save(user);
    }
}
