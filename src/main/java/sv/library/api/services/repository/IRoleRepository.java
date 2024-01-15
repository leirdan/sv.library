package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.library.api.domain.Role;
import sv.library.api.utils.RoleName;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
