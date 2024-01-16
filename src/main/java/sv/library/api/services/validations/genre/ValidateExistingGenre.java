package sv.library.api.services.validations.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.validations.interfaces.IValidatorGenre;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingGenre implements IValidatorGenre {
    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public void validate(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new ElementNotFoundOnDBException(String.format("Genre with id %d doesn't exist!", id));
        }
    }

}
