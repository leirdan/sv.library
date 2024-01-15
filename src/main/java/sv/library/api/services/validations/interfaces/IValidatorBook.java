package sv.library.api.services.validations.interfaces;

import sv.library.api.dto.books.CreateBookData;

public interface IValidatorBook {
    public void validate(CreateBookData data);
}
