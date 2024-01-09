package sv.library.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.dto.auth.CreateLoginData;
import sv.library.api.dto.auth.TokenData;
import sv.library.api.services.TokenService;
import sv.library.api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager _authManager;
    @Autowired
    private TokenService _tokenService;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid CreateLoginData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = _authManager.authenticate(authToken);

        String jwtToken = _tokenService.generateToken((UserService) auth.getPrincipal());

        return ResponseEntity.ok(new TokenData(jwtToken));
    }
}
