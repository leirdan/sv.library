package sv.library.api.services;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Loan;
import sv.library.api.domain.Status;
import sv.library.api.domain.User;
import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.repository.ILoanRepository;
import sv.library.api.services.repository.IStatusRepository;
import sv.library.api.utils.Database;

@DataJpaTest // Teste de repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Não substitua o DB da aplicação por um
                                                                             // in-memory
@ActiveProfiles("test") // Lê o perfil application-test
public class ILoanRepositoryTest {

    @Autowired
    private ILoanRepository loanRepository;
    @Autowired
    private IGenreRepository genreRepository;
    @Autowired
    private IStatusRepository statusRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar nulo quando nao existe emprestimo para id de usuario e livro")
    void case1() {
        // Arrange
        User user = Database.loadUsers().get(0);
        Book book = Database.loadBooks().get(1);

        // Act
        Long id = loanRepository.findLoanIdByUserAndBookIds(book.getId(), user.getId());

        // Assert
        Assertions.assertThat(id).isNull();
    }

    @Test
    @DisplayName("Deve retornar um Id quando existe emprestimo para id de usuario e livro")
    void case2() {
        // Arrange
        User user = Database.loadUsers().get(0);
        Book book = Database.loadBooks().get(1);

        // Act
        createLoan(book, user);
        Long id = loanRepository.findLoanIdByUserAndBookIds(book.getId(), user.getId());

        // Assert
        Assertions.assertThat(id).isPositive();
    }

    void createLoan(Book book, User user) {
        if (entityManager.find(book.getClass(), book.getId()) == null) {
            createBook(book);
        }
        if (entityManager.find(user.getClass(), user.getId()) == null) {
            createUser(user);
        }

        entityManager.merge(
                new Loan(null, book, user, LocalDateTime.now().plusDays(15), LocalDateTime.now(), false, null, true));
    }

    void createBook(Book book) {
        if (!genreRepository.existsById(book.getGenre().getId())) {
            createGenre(book.getGenre());
        }
        if (!statusRepository.existsById(book.getStatus().getId())) {
            createStatus(book.getStatus());
        }
        entityManager.merge(book);
    }

    void createUser(User user) {
        entityManager.merge(user);
    }

    void createGenre(Genre genre) {
        entityManager.merge(genre);
    }

    void createStatus(Status status) {
        entityManager.merge(status);
    }
}
