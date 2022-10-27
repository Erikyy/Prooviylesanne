package ee.erik.core.infrastructure.persistance.wrappers;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.core.infrastructure.persistance.repositories.DbParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DomainParticipantRepository implements ParticipantRepository {

    private DbParticipantRepository participantRepository;

    @Autowired
    public DomainParticipantRepository(DbParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Optional<Participant> findById(Long id) {
        Optional<ParticipantEntity> participantEntity = this.participantRepository.findById(id);
        if (participantEntity.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(participantEntity.get().toParticipant());
        }
    }

    @Override
    public Set<Participant> findParticipantsByEvent(Event event) {
        return this.participantRepository.findAllByEventId(event.getId()).stream().map(ParticipantEntity::toParticipant).collect(Collectors.toSet());
    }

    @Override
    public void delete(Participant participant) {
        this.participantRepository.delete(ParticipantEntity.toEntity(participant));
    }

    @Override
    public Participant save(Participant participant) {
        return this.participantRepository.save(ParticipantEntity.toEntity(participant)).toParticipant();
    }
}
