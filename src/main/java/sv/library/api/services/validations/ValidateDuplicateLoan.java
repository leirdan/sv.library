package sv.library.api.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.ILoanRepository;

@Component
public class ValidateDuplicateLoan implements IValidator {
    @Autowired
    private ILoanRepository loanRepository;

    public void validate(CreateLoanDTO data) {
        if (loanRepository.existsDuplicatedLoan(Integer.parseInt(data.bookId().toString()),
                Integer.parseInt(data.userId().toString())) != 0) {
            throw new ValidationException("The user already has already borrowed the book!");
        }
    }
}