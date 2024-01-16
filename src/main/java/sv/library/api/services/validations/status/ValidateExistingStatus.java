package sv.library.api.services.validations.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.services.repository.IStatusRepository;
import sv.library.api.services.validations.interfaces.IValidatorStatus;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingStatus implements IValidatorStatus {

    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public void validate(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new ElementNotFoundOnDBException(String.format("Status with id %d doesn't exist!", id));
        }
    }

}
