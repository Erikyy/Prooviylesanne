package ee.erik.backend.application.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCitizenDto {
    private String lastName;
    private Long idNumber;
    private String info;
}
