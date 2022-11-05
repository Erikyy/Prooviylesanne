package ee.erik.backend.application.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {
    private Long id;
    private PaymentMethodDto paymentMethod;
    private String name;
    private CitizenDto citizen;
    private BusinessDto business;
}
