package ee.erik.core.application.dto;

import ee.erik.core.domain.entities.participant.PaymentMethod;
import lombok.Data;

import java.util.Optional;

@Data
public class CreateParticipantDto {
    private Long id;
    private PaymentMethod paymentMethod;
    private String name;

    private CreateCitizenDto citizen;
    private CreateBusinessDto business;

}
