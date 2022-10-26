package ee.erik.core.application;

import ee.erik.core.application.managers.EventsManager;
import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Configuration
public class DatabaseInit {

    @Bean
    CommandLineRunner initDb(EventsManager manager) {

        return args -> {
            Event event = new Event();
            event.setName("Test");
            event.setDate(Date.from(LocalDate.now().plusMonths(2).atStartOfDay().toInstant(ZoneOffset.UTC)));
            event.setLocation("Test");
            event.setInfo("Test test");
            manager.addEvent(event);


            Citizen citizen = new Citizen();
            citizen.setLastName("Test");
            citizen.setIdNumber(2354354352L);
            citizen.setInfo("Test");

            Participant participant = new Participant();
            participant.setPaymentMethod(PaymentMethod.BankTransfer);
            participant.setEvent(event);
            participant.setName("Test");
            participant.setCitizen(citizen);

            manager.addParticipant(participant, 10L);


        };
    }
}
