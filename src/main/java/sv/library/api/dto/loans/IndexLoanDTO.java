package sv.library.api.dto.loans;

import java.time.LocalDateTime;

public record IndexLoanDTO(
        Long id,
        Long bookId,
        Long userId,
        LocalDateTime expirationDateTime,
        LocalDateTime createdAt) {
}
