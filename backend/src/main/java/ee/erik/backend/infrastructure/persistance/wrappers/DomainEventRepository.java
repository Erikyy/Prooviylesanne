package ee.erik.backend.infrastructure.persistance.wrappers;

import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DomainEventRepository implements EventRepository {

    private final DbEventRepository eventRepository;

    @Autowired
    public DomainEventRepository(DbEventRepository dbEventRepository) {
        this.eventRepository = dbEventRepository;
    }

    @Override
    public Optional<Event> findById(Long id) {
        Optional<EventEntity> dbEvent = this.eventRepository.findById(id);
        return dbEvent.map(EventEntity::toEvent);
    }

    @Override
    public Set<Event> findAll() {
        return this.eventRepository.findAllSet().stream().map(EventEntity::toEvent).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> findAllBeforeDate(Date date) {
        return this.eventRepository.findAllBeforeDate(date).stream().map(EventEntity::toEvent).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> findAllAfterDate(Date date) {
        return this.eventRepository.findAllAfterDate(date).stream().map(EventEntity::toEvent).collect(Collectors.toSet());
    }

    @Override
    public void delete(Event event) {
        this.eventRepository.delete(EventEntity.toEntity(event));
    }

    @Override
    public Event save(Event event) {
        EventEntity eventEntity = this.eventRepository.save(EventEntity.toEntity(event));
        return eventEntity.toEvent();
    }
}
