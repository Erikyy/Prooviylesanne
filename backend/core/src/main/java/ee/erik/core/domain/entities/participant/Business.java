package ee.erik.core.domain.entities.participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private Long id;
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
