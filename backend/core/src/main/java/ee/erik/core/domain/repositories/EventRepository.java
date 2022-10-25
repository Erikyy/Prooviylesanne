package ee.erik.core.domain.repositories;

import ee.erik.core.domain.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    int count();

    void save(Event event);

    void update(Event event);

    void deleteById(Long id);

    List<Event> findAll();

    Optional<Event> findById(Long id);
}
