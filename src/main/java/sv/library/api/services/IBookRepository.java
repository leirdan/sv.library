package sv.library.api.services;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.library.api.domain.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
