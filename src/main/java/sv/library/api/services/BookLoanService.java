package sv.library.api.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.library.api.domain.Book;
import sv.library.api.domain.Loan;
import sv.library.api.domain.User;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.services.repository.IBookRepository;
import sv.library.api.services.repository.ILoanRepository;
import sv.library.api.services.repository.IUserRepository;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

@Service
public class BookLoanService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ILoanRepository loanRepository;

    public void lendBook(CreateLoanDTO data) {
        if (!bookRepository.existsById(data.bookId())) {
            throw new ElementNotFoundOnDBException(String.format("Book with id %d doesn't exist!", data.bookId()));
        }
        if (!userRepository.existsById(data.userId())) {
            throw new ElementNotFoundOnDBException(String.format("User with id %d doesn't exist!", data.userId()));
        }

        Book book = bookRepository.findById(data.bookId()).get();
        User user = userRepository.findById(data.userId()).get();

        Loan loan = new Loan(
                null,
                book,
                user,
                LocalDateTime.now().plusDays(15),
                LocalDateTime.now(),
                false,
                true);

        loanRepository.save(loan);
    }
}
