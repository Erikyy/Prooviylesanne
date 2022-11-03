package ee.erik.backend.integration.repository;

import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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

}
