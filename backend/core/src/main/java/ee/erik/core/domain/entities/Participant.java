package ee.erik.core.domain.entities;

import ee.erik.core.domain.entities.participant.Business;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private Long id;
    private PaymentMethod paymentMethod;
    private String name;
    private Citizen citizen;
    private Business business;
}
