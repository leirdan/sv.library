package sv.library.api.services.validations.interfaces;

import sv.library.api.dto.books.BookData;

public interface IValidatorBook {
    public void validate(BookData data);

    public void validate(Long id);
}
