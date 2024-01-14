package sv.library.api.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.library.api.domain.Loan;

public interface ILoanRepository extends JpaRepository<Loan, Long> {

}
