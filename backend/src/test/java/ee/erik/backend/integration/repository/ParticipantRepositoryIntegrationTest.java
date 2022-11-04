package ee.erik.backend.integration.repository;

import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class ParticipantRepositoryIntegrationTest {
    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");


    @DynamicPropertySource
    static void registerDb(DynamicPropertyRegistry registry) {
        //here is a problem with setting these properties
        System.out.println(container.getJdbcUrl());
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private ParticipantRepository participantRepository;


    @Autowired
    private DbParticipantRepository dbParticipantRepository;

    //required for saving new participant
    @Autowired
    private DbPaymentMethodRepository dbPaymentMethodRepository;

    @Test
    public void shouldSaveWithEventAndReturnAllByEvent() {

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity();
        paymentMethod.setMethod("Test");

        PaymentMethodEntity paymentMethodEntity = this.dbPaymentMethodRepository.save(paymentMethod);


        ParticipantEntity participant = new ParticipantEntity();
        participant.setPaymentMethod(AggregateReference.to(paymentMethodEntity.getId()));
        participant.setName("Test");

        ParticipantEntity saved = this.dbParticipantRepository.save(participant);

        Optional<Participant> domainParticipant = this.participantRepository.findById(saved.getId());

        assertThat(domainParticipant).isPresent();
        assertThat(domainParticipant.get()).isEqualTo(saved.toParticipant());

    }

}
