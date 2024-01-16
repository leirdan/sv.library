package sv.library.api.services.validations.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.ILoanRepository;
import sv.library.api.services.validations.interfaces.IValidatorLoan;

@Component
public class ValidateDuplicateLoan implements IValidatorLoan {
    @Autowired
    private ILoanRepository loanRepository;

    public void validate(CreateLoanDTO data) {
        if (loanRepository.findLoanIdByUserAndBookIds(data.bookId(), data.userId()) != null) {
            throw new ValidationException("The user already has already borrowed the book!");
        }
    }
}