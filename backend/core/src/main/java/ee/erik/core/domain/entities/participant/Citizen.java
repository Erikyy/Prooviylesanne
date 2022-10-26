package ee.erik.core.domain.entities.participant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Citizen {
    private Long id;
    private String lastName;
    private Long idNumber;
    private String info;
}
