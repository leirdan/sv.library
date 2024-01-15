package sv.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sv.library.api.dto.loans.CreateLoanDTO;
import sv.library.api.dto.loans.IndexLoanDTO;
import sv.library.api.services.BookLoanService;

@RestController
@RequestMapping("/emprestimos")
public class LoanController {
    @Autowired
    private BookLoanService loanService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<IndexLoanDTO> lend(@RequestBody @Valid CreateLoanDTO data) {
        IndexLoanDTO loan = loanService.lendBook(data);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/darBaixa")
    @Transactional
    public ResponseEntity writeOff(@RequestBody @Valid CreateLoanDTO data) {
        loanService.writeOffLoan(data);
        return ResponseEntity.noContent().build();
    }
}
