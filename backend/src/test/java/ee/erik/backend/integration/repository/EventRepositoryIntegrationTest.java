package ee.erik.backend.integration.repository;

import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class EventRepositoryIntegrationTest {
    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");


    @DynamicPropertySource
    static void registerDb(DynamicPropertyRegistry registry) {
        System.out.println(container.getJdbcUrl());
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DbEventRepository dbEventRepository;

    @Test
    public void shouldCreateEvent() {

        Date beforeDate = Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC));
        Date afterDate = Date.from(LocalDate.now().minusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC));

        EventEntity beforeDateEvent = new EventEntity();
        beforeDateEvent.setName("Test Before");
        beforeDateEvent.setDate(beforeDate);
        beforeDateEvent.setLocation("Test");
        beforeDateEvent.setInfo("Test");

        EventEntity afterDateEvent = new EventEntity();
        afterDateEvent.setName("Test after");
        afterDateEvent.setDate(afterDate);
        afterDateEvent.setLocation("Test");
        afterDateEvent.setInfo("Test");

        this.dbEventRepository.save(beforeDateEvent);
        this.dbEventRepository.save(afterDateEvent);

        assertThat(this.dbEventRepository.findAll()).isNotEmpty();
        assertThat(this.eventRepository.findAll()).isNotEmpty();

    }

    @Test
    public void shouldListEventsBeforeDate() {

    }

    @Test
    public void shouldListEventsAfterDate() {

    }
}
