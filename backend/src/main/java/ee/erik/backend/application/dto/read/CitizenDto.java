package ee.erik.backend.application.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenDto {
    private Long id;
    private String lastName;
    private Long idNumber;
    private String info;
}
