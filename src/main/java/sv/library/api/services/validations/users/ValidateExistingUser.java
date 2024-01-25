package sv.library.api.services.validations.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.IUserRepository;
import sv.library.api.services.validations.interfaces.IValidatorLoan;
import sv.library.api.services.validations.interfaces.IValidatorUser;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingUser implements IValidatorLoan, IValidatorUser {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void validate(CreateLoanDTO data) {
        if (!userRepository.existsById(data.userId())) {
            throw new ElementNotFoundOnDBException(String.format("User with id %d doesn't exist!", data.userId()));
        }
    }

    @Override
    public void validate(Long id) {
        if (!userRepository.existsById(id)){
            throw new ElementNotFoundOnDBException(String.format("User with id %d doesn't exist!", id));
        }
    }

}
