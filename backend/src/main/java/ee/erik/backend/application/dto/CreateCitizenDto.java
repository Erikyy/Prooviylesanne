package ee.erik.backend.application.dto;

import lombok.Data;

@Data
public class CreateCitizenDto {
    private String lastName;
    private Long idNumber;
    private String info;
}
