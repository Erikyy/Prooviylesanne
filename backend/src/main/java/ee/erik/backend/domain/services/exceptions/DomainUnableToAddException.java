package ee.erik.backend.domain.services.exceptions;

public class DomainUnableToAddException extends RuntimeException {

    public DomainUnableToAddException(String message) {
        super(message);
    }
}
