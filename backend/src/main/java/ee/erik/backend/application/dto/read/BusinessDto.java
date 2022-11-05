package ee.erik.backend.application.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {
    private Long id;
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
