package sv.library.api.services;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.Genre;

public interface IGenreRepository extends JpaRepository<Genre, Long> {
}
