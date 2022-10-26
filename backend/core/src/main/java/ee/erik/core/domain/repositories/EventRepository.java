package ee.erik.core.domain.repositories;

import ee.erik.core.domain.entities.Event;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EventRepository {

    int count();

    Event save(Event event);

    Event update(Event event);

    void deleteById(Long id);

    List<Event> findAll();

    Optional<Event> findById(Long id);
}
