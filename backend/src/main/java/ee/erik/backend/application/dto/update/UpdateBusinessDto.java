package ee.erik.backend.application.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBusinessDto {
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
