package ee.erik.backend.application.dto.update;

import ee.erik.backend.application.dto.read.BusinessDto;
import ee.erik.backend.application.dto.read.CitizenDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParticipantDto {
    private Long paymentMethodId;
    private String name;
    private UpdateCitizenDto citizen;
    private UpdateBusinessDto business;
}
