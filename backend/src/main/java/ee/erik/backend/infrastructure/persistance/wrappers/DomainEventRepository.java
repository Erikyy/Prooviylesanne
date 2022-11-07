package ee.erik.backend.infrastructure.persistance.wrappers;

import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.infrastructure.persistance.entities.ParticipantRef;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
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

    @Override
    public Optional<Event> saveWithParticipant(Event event, Long participantRefId) {
        Optional<EventEntity> optionalEventEntity = this.eventRepository.findById(event.getId());
        if (optionalEventEntity.isPresent()) {
            if (optionalEventEntity.get().getId() == null) {
                return Optional.empty();
            } else {
                EventEntity eventEntity = optionalEventEntity.get();
                eventEntity.setName(event.getName());
                eventEntity.setInfo(event.getInfo());
                eventEntity.setLocation(event.getLocation());
                eventEntity.setDate(event.getDate());
                ParticipantRef participantRef = new ParticipantRef();
                participantRef.setParticipantId(AggregateReference.to(participantRefId));
                eventEntity.getParticipantEntities().add(participantRef);
                return Optional.of(this.eventRepository.save(eventEntity).toEvent());
            }
        } else {
            return Optional.empty();
        }

    }
}
