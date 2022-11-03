package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.Event;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface EventRepository {

    Optional<Event> findById(Long id);

    Set<Event> findAll();

    Set<Event> findBeforeDate(Date date);

    Set<Event> findAfterDate(Date date);

    void delete(Event event);

    Event save(Event event);
}
