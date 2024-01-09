package sv.library.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${svlibrary.api.security.token.secret}")
    private String key;

    public String generateToken(UserService user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);

            return JWT.create()
                    .withIssuer("API SvLibrary")
                    .withSubject(user.getUsername().toString())
                    .withExpiresAt(getExpirationDate())
                    .withClaim("id", user.getUser().getId())
                    .sign(algorithm);

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while generating JWT token.");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(23).toInstant(ZoneOffset.of("-03:00"));
    }
}
