package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.Event;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Infrastructure uses this to convert database entities to domain entities and back
 */
public interface EventRepository {

    Optional<Event> findById(Long id);

    Set<Event> findAll();

    /**
     * Find events before some date
     * @param date
     * @return Set of domain events
     */
    Set<Event> findAllBeforeDate(Date date);

    /**
     * Find events before some date
     * @param date
     * @return Set of domain events
     */
    Set<Event> findAllAfterDate(Date date);

    void delete(Event event);

    Event save(Event event);

    /**
     * Saves with participant reference id
     * This function will return empty if event id doesn't exist
     * @param event existing event
     * @param participantRefId participant reference
     * @return domain event as optional
     */
    Optional<Event> saveWithParticipant(Event event, Long participantRefId);
}
