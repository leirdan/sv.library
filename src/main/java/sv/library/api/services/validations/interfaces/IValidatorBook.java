package sv.library.api.services.validations.interfaces;

import sv.library.api.dto.books.BookDTO;

public interface IValidatorBook {
    public void validate(BookDTO data);

    public void validate(Long id);
}
