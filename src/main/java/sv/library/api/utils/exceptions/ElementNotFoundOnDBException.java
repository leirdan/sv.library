package sv.library.api.utils.exceptions;

public class ElementNotFoundOnDBException extends RuntimeException {

    public ElementNotFoundOnDBException(String errorMessage) {
        super(errorMessage);
    }

}
