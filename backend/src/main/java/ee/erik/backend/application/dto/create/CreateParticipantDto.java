package ee.erik.backend.application.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipantDto {
    private Long id;
    private Long paymentMethodId;
    private String name;
    private CreateCitizenDto citizen;
    private CreateBusinessDto business;

}
