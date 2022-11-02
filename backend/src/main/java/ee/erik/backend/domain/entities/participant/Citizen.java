package ee.erik.backend.domain.entities.participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
    private Long id;
    private String lastName;
    private Long idNumber;
    private String info;
}
