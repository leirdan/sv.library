package sv.library.api.services.validations.interfaces;

import sv.library.api.dto.loans.CreateLoanDTO;

public interface IValidatorLoan {
    void validate(CreateLoanDTO data);
}
