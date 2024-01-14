package sv.library.api.dto.loans;

import jakarta.validation.constraints.NotNull;

public record CreateLoanDTO(@NotNull Long bookId, @NotNull Long userId) {

}
