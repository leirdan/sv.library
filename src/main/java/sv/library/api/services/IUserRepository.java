package sv.library.api.services;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {
}
