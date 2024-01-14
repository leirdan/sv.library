package sv.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.dto.loans.IndexLoanDTO;
import sv.library.api.services.BookLoanService;

@RestController
@RequestMapping("/emprestimos")
public class LoanController {
    @Autowired
    private BookLoanService loanService;

    @PostMapping
    public ResponseEntity lend(@RequestBody @Valid CreateLoanDTO data) {
        loanService.lendBook(data);
        return ResponseEntity.ok(new IndexLoanDTO(null, null, null, null, null));
    }
}
