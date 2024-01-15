package sv.library.api.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.dto.genre.CreateGenreData;
import sv.library.api.dto.genre.GenreData;
import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.validations.interfaces.IValidatorGenre;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingGenre implements IValidatorGenre {
    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public void validate(GenreData data) {
        if (!genreRepository.existsById(data.id())) {
            throw new ElementNotFoundOnDBException(String.format("Genre with id %d doesn't exist!", data.id()));
        }
    }

}
