package ee.erik.backend.application.dto;

import ee.erik.backend.domain.entities.participant.PaymentMethod;
import lombok.Data;

import java.util.Optional;

@Data
public class CreateParticipantDto {
    private Long id;
    private Long paymentMethodId;
    private String name;

    private CreateCitizenDto citizen;
    private CreateBusinessDto business;

}
