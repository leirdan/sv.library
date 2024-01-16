package sv.library.api.services.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import sv.library.api.domain.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByActiveTrue(Pageable pageable);
}
