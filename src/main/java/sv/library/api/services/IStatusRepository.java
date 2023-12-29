package sv.library.api.services;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.Status;

public interface IStatusRepository extends JpaRepository<Status, Long> {
}
