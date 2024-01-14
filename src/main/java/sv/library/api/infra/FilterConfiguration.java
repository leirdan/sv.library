package sv.library.api.infra;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import sv.library.api.services.TokenService;
import sv.library.api.services.impl.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component // Spring carrega um componente genérico
public class FilterConfiguration extends OncePerRequestFilter {

    @Autowired
    private TokenService _tokenService;
    @Autowired
    private UserDetailsServiceImpl _userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (endpointRequiresAuth(request)) {
            String token = retrieveToken(request);

            if (token != null) {
                String subject = _tokenService.getSubject(token);

                UserDetails user = _userDetailsService.loadUserByUsername(subject);

                if (user != null) {
                    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null) {
            return header.replace("Bearer", "").trim();
        }
        return null;
    }

    private boolean endpointRequiresAuth(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        List<String> endpoints = Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_NO_AUTHENTICATION);
        if (endpoints.contains(requestURI.trim())) {
            return false;
        }
        return true;
    }
}
