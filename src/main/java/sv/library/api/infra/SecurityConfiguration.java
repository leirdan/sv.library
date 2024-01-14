package sv.library.api.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
@AllArgsConstructor
// Configurações do Spring Security
public class SecurityConfiguration {
    @Autowired
    private FilterConfiguration _filterConfiguration;

    public static final String[] ENDPOINTS_WITH_NO_AUTHENTICATION = {
            "/auth/login",
            "/auth/cadastro",

    };

    public static final String[] ENDPOINTS_EMPLOYEE = {
            "/usuarios",
            "/status"
    };

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http.csrf(c -> c.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(r -> {
                    r.requestMatchers(ENDPOINTS_WITH_NO_AUTHENTICATION).permitAll();
                    r.requestMatchers(ENDPOINTS_EMPLOYEE).hasRole("EMPLOYEE");
                    r.anyRequest().authenticated();
                })
                .addFilterBefore(_filterConfiguration, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Configuração da injeção de dependência de AuthenticationManager no
    // AuthController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuração do algoritmo de criptografia de senhas BCrypt
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
