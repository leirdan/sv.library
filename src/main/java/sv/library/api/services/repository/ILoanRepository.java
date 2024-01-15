package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sv.library.api.domain.Loan;

public interface ILoanRepository extends JpaRepository<Loan, Long> {
    @Query("select count(*) from Emprestimo e where e.book.id = :bookId and e.user.id = :userId and e.active = true")
    public int existsDuplicatedLoan(int bookId, int userId);
}
