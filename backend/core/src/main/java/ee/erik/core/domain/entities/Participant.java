package ee.erik.core.domain.entities;

import ee.erik.core.domain.entities.participant.Business;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Participant {
    private Long id;
    private Event event;
    private PaymentMethod paymentMethod;
    private String name;
    private Citizen citizen;
    private Business business;
}
