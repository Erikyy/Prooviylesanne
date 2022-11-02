package ee.erik.backend.application;

import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.CitizenEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
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

        };
    }
}
