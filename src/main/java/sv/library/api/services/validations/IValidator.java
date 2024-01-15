package sv.library.api.services.validations;

import sv.library.api.dto.loans.CreateLoanDTO;

public interface IValidator {
    void validate(CreateLoanDTO data);
}
