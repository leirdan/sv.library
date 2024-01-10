package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
