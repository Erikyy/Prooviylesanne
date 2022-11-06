package ee.erik.backend.domain.entities;

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
