package sv.library.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.dto.auth.CreateLoginData;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager _authManager;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid CreateLoginData data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        _authManager.authenticate(token);
        return ResponseEntity.ok().build();
        // não está autenticando...
    }
}
