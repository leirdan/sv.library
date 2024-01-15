package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import sv.library.api.domain.Loan;

public interface ILoanRepository extends JpaRepository<Loan, Long> {
    @Query("select e.id from Emprestimo e where e.book.id = :bookId and e.user.id = :userId and e.active = true")
    public Long findLoanIdByUserAndBookIds(Long bookId, Long userId);

    @Modifying
    @Query("update Emprestimo e set e.active = false, e.returned = true, e.devolutionDate = CURRENT_TIMESTAMP where e.id = :id")
    public void registerDevolution(Long id);
}
