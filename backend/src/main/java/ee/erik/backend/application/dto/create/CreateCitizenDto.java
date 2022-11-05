package ee.erik.backend.application.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCitizenDto {
    private String lastName;
    private Long idNumber;
    private String info;
}
