package sv.library.api.infra;

import jakarta.persistence.EntityNotFoundException;
import sv.library.api.utils.exceptions.ElementNotFoundOnDBException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> Error404(EntityNotFoundException ex) {
        String err = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> Error400(SQLIntegrityConstraintViolationException ex) {
        String err = ex.getMessage();
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ElementNotFoundOnDBException.class)
    public ResponseEntity<String> Error400(ElementNotFoundOnDBException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorData>> Error400InvalidArgument(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorData::new).toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> Error401InvalidCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> Error401AuthFailed() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> Error403() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
    }

    private record ErrorData(String field, String msg) {
        public ErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
