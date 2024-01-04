package sv.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.library.api.domain.User;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private IUserRepository _userRepository;
    @Override
    // Retorna um objeto UserDetails com base no username fornecido. Utilizado em AuthController pelo método authenticate.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByLogin(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new RoleService(user);
    }
}

