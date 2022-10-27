package ee.erik.core.domain.repositories;

import ee.erik.core.domain.entities.Event;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface EventRepository {

    Optional<Event> findById(Long id);

    Set<Event> findBeforeDate(Date date);

    Set<Event> findAfterDate(Date date);

    void delete(Event event);

    Event save(Event event);
}
