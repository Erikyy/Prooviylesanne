package ee.erik.core.infrastructure.persistance;


import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.domain.repositories.participant.BusinessRepository;
import ee.erik.core.domain.repositories.participant.CitizenRepository;
import ee.erik.core.infrastructure.persistance.repositories.EventRepositoryImpl;
import ee.erik.core.infrastructure.persistance.repositories.ParticipantRepositoryImpl;
import ee.erik.core.infrastructure.persistance.repositories.participant.BusinessRepositoryImpl;
import ee.erik.core.infrastructure.persistance.repositories.participant.CitizenRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistanceConfiguration {

    @Bean
    public EventRepository eventRepository() {
        return new EventRepositoryImpl();
    }

    @Bean
    public ParticipantRepository participantRepository() {
        return new ParticipantRepositoryImpl();
    }

    @Bean
    public CitizenRepository citizenRepository() { return new CitizenRepositoryImpl(); }

    @Bean
    public BusinessRepository businessRepository() { return new BusinessRepositoryImpl(); }
}
