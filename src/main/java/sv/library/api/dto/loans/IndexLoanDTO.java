package sv.library.api.dto.loans;

import java.time.LocalDateTime;

import sv.library.api.domain.Loan;

public record IndexLoanDTO(
                Long id,
                Long bookId,
                Long userId,
                LocalDateTime expirationDateTime,
                LocalDateTime createdAt) {
        public IndexLoanDTO(Loan loan) {
                this(loan.getId(), loan.getBook().getId(), loan.getUser().getId(), loan.getExpirationDate(),
                                loan.getCreatedAt());
        }
}
