package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.Genre;

public interface IGenreRepository extends JpaRepository<Genre, Long> {
}
