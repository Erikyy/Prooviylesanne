package ee.erik.core.domain.entities.participant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Business {
    private Long id;
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
