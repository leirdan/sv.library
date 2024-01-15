package sv.library.api.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.IUserRepository;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingUser implements IValidator {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void validate(CreateLoanDTO data) {
        if (!userRepository.existsById(data.userId())) {
            throw new ElementNotFoundOnDBException(String.format("User with id %d doesn't exist!", data.userId()));
        }
    }

}
