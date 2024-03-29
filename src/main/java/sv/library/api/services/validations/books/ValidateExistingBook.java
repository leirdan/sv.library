package sv.library.api.services.validations.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.library.api.dto.books.BookDTO;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.IBookRepository;
import sv.library.api.services.validations.interfaces.IValidatorBook;
import sv.library.api.services.validations.interfaces.IValidatorLoan;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Component
public class ValidateExistingBook implements IValidatorLoan, IValidatorBook {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void validate(CreateLoanDTO data) {
        if (!bookRepository.existsById(data.bookId())) {
            throw new ElementNotFoundOnDBException(String.format("Book with id %d doesn't exist!", data.bookId()));
        }
    }

    @Override
    public void validate(BookDTO data) {
        if (!bookRepository.existsById(data.id())) {
            throw new ElementNotFoundOnDBException(String.format("Book with id %d doesn't exist!", data.id()));
        }
    }

    @Override
    public void validate(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ElementNotFoundOnDBException(String.format("Book with id %d doesn't exist!", id));
        }
    }

}
