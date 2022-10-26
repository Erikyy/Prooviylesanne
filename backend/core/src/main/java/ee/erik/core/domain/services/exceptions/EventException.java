package ee.erik.core.domain.services.exceptions;

import ee.erik.core.domain.entities.Event;
import lombok.Getter;

public class EventException extends Exception {

    public enum EventExceptionStatus {
        FailedToCreate,
        FailedToDelete,
        EventNotFound,
        ParticipantNotFound,
        DateHasAlreadyPassed,
        InvalidUpdate
    }

    @Getter
    private EventExceptionStatus status;
    @Getter
    private Long id;

    public EventException(EventExceptionStatus status, Long id) {
        super();
        this.status = status;
        this.id = id;
    }
}
