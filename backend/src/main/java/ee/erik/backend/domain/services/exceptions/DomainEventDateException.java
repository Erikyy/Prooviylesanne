package ee.erik.backend.domain.services.exceptions;

public class DomainEventDateException extends RuntimeException {
    public DomainEventDateException(String message) {
        super(message);
    }
}
