package ee.erik.core.application;

import ee.erik.core.domain.entities.participant.PaymentMethod;
import ee.erik.core.infrastructure.persistance.entities.EventEntity;
import ee.erik.core.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.core.infrastructure.persistance.entities.participant.CitizenEntity;
import ee.erik.core.infrastructure.persistance.repositories.DbEventRepository;
import ee.erik.core.infrastructure.persistance.repositories.DbParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DatabaseInit {

    @Bean
    CommandLineRunner initDb(DbEventRepository dbEventRepository, DbParticipantRepository dbParticipantRepository) {

        return args -> {


            CitizenEntity citizenEntity = new CitizenEntity();
            citizenEntity.setInfo("Test");
            citizenEntity.setIdNumber(137642324332L);
            citizenEntity.setLastName("Test");

            ParticipantEntity participantEntity = new ParticipantEntity();

            participantEntity.setName("Test test");
            participantEntity.setCitizenEntity(citizenEntity);
            participantEntity.setPaymentMethod(PaymentMethod.Cash);


            Set<ParticipantEntity> participantEntities = new HashSet<>();

            participantEntities.add(participantEntity);

            EventEntity eventEntity = new EventEntity();
            eventEntity.setName("Test");
            eventEntity.setDate(new Date());
            eventEntity.setLocation("Location");
            eventEntity.setInfo("Test test");
            eventEntity.setParticipantEntities(participantEntities);
            dbEventRepository.save(eventEntity);



        };
    }
}
