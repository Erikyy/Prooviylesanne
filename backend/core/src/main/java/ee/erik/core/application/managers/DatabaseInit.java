package ee.erik.core.application.managers;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.repositories.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
public class DatabaseInit {

    @Bean
    CommandLineRunner initDb(EventRepository eventRepository) {

        return args -> {
            Event event = new Event();
            event.setName("Test");
            event.setDate(new Date());
            event.setLocation("Test");
            event.setInfo("Test test");
            eventRepository.save(event);
        };
    }
}
